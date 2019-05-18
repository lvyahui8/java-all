package org.lyh.camel.data;

import org.lyh.camel.bean.API;
import org.lyh.camel.config.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 22:37
 */
public class APIManager {

    private static List<API> apis;

    private static Map<String,API> apiDict;

    static{
        apis = new ArrayList<API>();

        apis.add(new API().setMethod(Method.GET).setPath("test").setUri("app/list")
                .setRealUrl("http://movesun.com/demo/json"));
        apis.add(new API().setMethod(Method.POST).setPath("test").setUri("app/save")
                .setRealUrl("http://movesun.com/json/push"));

        apiDict = new HashMap<String, API>();
        for (API api : apis){
            apiDict.put(api.getPath() + api.getUri(),api);
        }
    }

    public static List<API> getApis() {
        return apis;
    }

    public static Map<String, API> getApiDict() {
        return apiDict;
    }
}
