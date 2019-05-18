package org.lyh;

import redis.clients.jedis.Jedis;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/10/12 14:25
 */
public class SimpleRedis {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("admin888");
        String key = "simple-var";
        try{
            for(int i = 0 ; i < 10 ; i++){
                String v = jedis.get(key);
                Integer value = v != null ? Integer.valueOf(v) : 0;
                jedis.connect();
                jedis.set(key, String.valueOf(value + i));
                jedis.disconnect();
            }
            jedis.connect();
            System.out.println(jedis.get(key));
        } finally {
            jedis.close();
        }
    }
}
