package org.lyh.java.mybatis.bean;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2016/12/12 13:27
 */
@SuppressWarnings("unused")
public class Condition {

    public static final String LIKE = "like";
    public static final String LIKE_IG_CASE = "like_igcase";
    public static final String BETWEEN = "between";
    public static final String GT = ">";
    public static final String LT = "<";
    public static final String EQ = "=";
    public static final String NE = "!=";
    public static final String GE = ">=";
    public static final String LE = "<=";


    private String key;
    private String opt = "=";
    private Object value;

    public Condition(String key, String opt, Object value) {
        this.key = key;
        this.opt = opt;
        this.value = value;
    }

    public Condition(String key, Object value){
        this(key,"=",value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOpt() {
        return opt;
    }

    public void setOpt(String opt) {
        this.opt = opt;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
