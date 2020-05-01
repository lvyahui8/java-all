package org.lyh.base.ds.graph;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2019/12/4 23:20
 */
public class FullLinkGraphApp {
    /**
     * [ head ------->  tail ]
     */
    static class Edge {
        int tailVertexIndex;
        int headVertexIndex;
        Edge tailNextEdge;
        Edge headPrevEdge;
    }

    static class Vertex {
        String data;
        /**
         * 第1条出度
         */
        Edge firstOutput;
        /**
         * 第一条入度
         */
        Edge firstInput;

        public Vertex(String data) {
            this.data = data;
        }
    }

    static class DirectionalGraph {
        Map<String,Vertex> vertexMap;


        public DirectionalGraph(int size) {
            vertexMap = new HashMap<>(size);
        }
    }

    public static void main(String[] args) {
//        Map<String,Set<String> > receivingTable = new HashMap<>();
//        receivingTable.put("userWithPostLists", Collections.asSet("getUser","getPosts"));
//        receivingTable.put("userProfile", Collections.asSet("userWithPostLists","getPosts"));
//        DirectionalGraph graph = new DirectionalGraph(receivingTable.size());
//        for (Map.Entry<String,Set<String>> item : receivingTable.entrySet()) {
//            ///graph.vertexMap = new Vertex(item.getKey());
//        }

        try {
            func();
        } catch (Exception e) {

        }
    }

    private static void func() throws IllegalAccessException, InvocationTargetException {
        try {
            // throw new InterruptedException();
            throw new IllegalArgumentException("xxx");
        } catch (Exception e) {
            throw e;
        }
    }
}
