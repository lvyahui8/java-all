package org.lyh.agg;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Response;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.asynchttpclient.Dsl.asyncHttpClient;

/**
 * @author feego lvyahui8@gmail.com
 * @date 2020/9/12
 */
public class ParallelInvokeDemo {

    class ClientRequest {

    }

    interface IOWorker {
        void push(String s);
        ClientRequest popRequest();
    }


    public static class BizProcessor extends Thread {
        /**
         * 下游接口列表
         */
        List<String> apis = Arrays.asList(
                "http://www.example.com/api/a",
                "http://www.example.com/api/b",
                "http://www.example.com/api/c"
        ) ;

        /**
         * 基于NIO的下游调用http client
         */
        AsyncHttpClient dependApiInvoker = asyncHttpClient();

        /**
         * 存放下游调用分组的map
         */
        Map<CompletableFuture<Void>,List<CompletableFuture<Response>>> futureGroupMap = new LinkedHashMap<>();

        /**
         * 处理客户端套接字IO事件的IO线程。
         */
        IOWorker ioWorker;

        public BizProcessor(IOWorker ioWorker) {
            this.ioWorker = ioWorker;
        }

        @Override
        public void run() {
            /* while循环体内没有阻塞的代码 */
            while(true) {
                /* 轮询下游调用分组，有任何调用分组已经完成，则聚合结果，将结果转发给IO线程 */
                for (Iterator<Map.Entry<CompletableFuture<Void>,List<CompletableFuture<Response>>>> it
                     = futureGroupMap.entrySet().iterator();
                     it.hasNext();) {
                    Map.Entry<CompletableFuture<Void>,List<CompletableFuture<Response>>> futureGroupEntry = it.next();
                    CompletableFuture<Void> futureGroup = futureGroupEntry.getKey();
                    if (futureGroup.isDone()) {
                        // 组合a、b、c三个接口的响应, 这里随意组装仅作为示意
                        StringBuilder assembledResult = new StringBuilder();
                        for (CompletableFuture<Response> dependInvokeFuture : futureGroupEntry.getValue()) {
                            assembledResult.append(dependInvokeFuture.getNow(null));
                        }
                        // 通过内存队列， 转交给IO线程， 由IO线程序列化并响应给客户端
                        ioWorker.push(assembledResult.toString());

                        // 从当前业务线程中移除已调用完成的下游分组
                        it.remove();
                    }
                }

                /* 检查IO线程是否有新的请求进来， 如果有， 则将处理新的请求，并行发起异步NIO调用。将返回的CompletableFuture记录为一个分组， 由上面的循环去轮询结果 */
                ClientRequest clientRequest = ioWorker.popRequest(); // 不能阻塞
                if(clientRequest != null) {
                    List<CompletableFuture<Response>> dependFutureList = new LinkedList<>();
                    for (String api : apis) {
                        CompletableFuture<Response> dependInvokeFuture
                                = dependApiInvoker.prepareGet(api).execute().toCompletableFuture();
                        dependFutureList.add(dependInvokeFuture);
                    }
                    futureGroupMap.put(
                            /* 将多个下游调用合并为一个分组 */
                            CompletableFuture.allOf(dependFutureList.toArray(new CompletableFuture[0])),
                            dependFutureList
                    );
                }
            }
        }
    }

    public String someApi() throws InterruptedException, ExecutionException, TimeoutException {
        List<String> apis = Arrays.asList(
                "http://www.example.com/api/a",
                "http://www.example.com/api/b",
                "http://www.example.com/api/c"
        ) ;

        final HttpClient httpClient = HttpClientBuilder.create().build();
        List<CompletableFuture<HttpEntity>> futures = new LinkedList<>();
        for (String api : apis) {
            CompletableFuture<HttpEntity> future = CompletableFuture.supplyAsync(() -> {
                HttpResponse response = null;
                try {
                    response = httpClient.execute(new HttpGet(api));
                } catch (IOException e) {
                    return null;
                }
                return response.getEntity();
            });
            futures.add(future);
        }

        // 业务线程阻塞等待下游调用全部完成或者超时
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(2, TimeUnit.SECONDS);

        /* 组合下游调用的结果 */
        StringBuilder assembledResult = new StringBuilder();
        for (CompletableFuture<HttpEntity> future : futures) {
            assembledResult.append(read(future.getNow(null)));
        }
        return assembledResult.toString();
    }

//    public void someApi() {
//        return DataFacade.get(null, new Function3<HttpEntity,HttpEntity,HttpEntity, String>() {
//            /**
//             * 方法上注解了@DataConsumer的三个入参，会被并行查询并在查询结束后注入此方法
//             */
//            @Override
//            public String apply(@DataConsumer("apiA") HttpEntity aResult,
//                              @DataConsumer("apiB") HttpEntity bResult,
//                              @DataConsumer("apiC") HttpEntity cResult) {
//                return new StringBuilder()
//                        .append(read(aResult))
//                        .append(read(bResult))
//                        .append(read(cResult))
//                        ;
//            }
//        });
//    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        /**
         * 下游接口列表
         */
        List<String> apis = Arrays.asList(
                "http://www.example.com/api/a",
                "http://www.example.com/api/b",
                "http://www.example.com/api/c"
        ) ;

        final HttpClient httpClient = HttpClientBuilder.create().build();
        List<CompletableFuture<HttpEntity>> futures = new LinkedList<>();
        for (String api : apis) {
            CompletableFuture<HttpEntity> future = CompletableFuture.supplyAsync(() -> {
                HttpResponse response = null;
                try {
                    response = httpClient.execute(new HttpGet(api));
                } catch (IOException e) {
                    return null;
                }
                return response.getEntity();
            });
            futures.add(future);
        }

        // 业务线程阻塞等待下游调用全部完成或者超时
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).get(2, TimeUnit.SECONDS);

        /* 组合下游调用的结果 */
        StringBuilder assembledResult = new StringBuilder();
        for (CompletableFuture<HttpEntity> future : futures) {
            assembledResult.append(read(future.getNow(null)));
        }
        // return assembledResult.toString();
    }

    public static String read(Object obj) {
        return obj.toString();
    }
}
