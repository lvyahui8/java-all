package org.lyh.camel.bean;

import org.apache.camel.Exchange;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/1/11 14:25
 */
@SuppressWarnings("unused")
public class Input extends HashMap<String,Object> {
    public static Input createInstance(Exchange exchange){
        String queryStr = (String) exchange.getIn().getHeader(Exchange.HTTP_QUERY);
        Input input = new Input();
        if(queryStr == null){
            if((queryStr = exchange.getIn().getBody(String.class)) == null){
                return input;
            }
        }
        String compsQueryStr;
        try {
            compsQueryStr = URLDecoder.decode(queryStr, "UTF-8");
            if(compsQueryStr.equals(queryStr)){
               compsQueryStr = queryStr;
            }
        } catch (UnsupportedEncodingException e) {
            //
            compsQueryStr = queryStr;
        }
        String[] splitParams = compsQueryStr.split("&");
        for (String item : splitParams){
            String[] kv = item.split("=");
            if(kv.length >0 ){
                input.put(kv[0],kv.length >= 2 ? kv[1] : true);
            }
        }
        return input;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key,Class<T> tClass){
        Object value = get(key);
        if(value == null){
            if( boolean.class.isAssignableFrom(tClass)){
                return (T) Boolean.FALSE;
            }
            return null;
        }

        if(tClass.isInstance(value)){
            return tClass.cast(value);
        }

        if(tClass == Integer.class){
            return (T) Integer.valueOf((String) value);
        }

        if(tClass == Boolean.class){
            return (T) Boolean.valueOf((String) value);
        }

        if(tClass == Float.class){
            return (T) Float.valueOf((String) value);
        }

        if(tClass == Double.class){
            return (T) Double.valueOf((String) value);
        }

        if(tClass == Date.class){
            //DateFormat format
            // = SimpleDateFormat.getDateTimeInstance(DateFormat.LONG,DateFormat.LONG);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            // regex match ...

            // pase
            try {
                return (T) format.parse((String) value);
            } catch (ParseException e) {
                //
                return tClass.cast(value);
            }
        }
        return tClass.cast(value);
    }

}
