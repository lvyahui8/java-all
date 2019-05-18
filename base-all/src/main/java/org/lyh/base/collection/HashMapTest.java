package org.lyh.base.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/4/10 16:00
 */
public class HashMapTest {
    public static void main(String[] args) {
        Map<String,Object> data = new HashMap<String, Object>();
        data.put("name",null);
        System.out.println(data.size());
        System.out.println(data);
    }
}
