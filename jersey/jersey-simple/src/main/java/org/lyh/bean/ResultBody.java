package org.lyh.bean;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lvyahui
 * @since 2016/9/13 14:31.
 */
public class ResultBody {
    @JsonProperty
    private int code;
    @JsonProperty
    private String msg;
    @JsonProperty
    private Map<String, Object> data = new HashMap<String, Object>();


    public int getCode() {
        return code;
    }

    public ResultBody setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public ResultBody setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResultBody setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    public ResultBody put(String dataItem, Object value) {
        this.data.put(dataItem, value);
        return this;
    }
}
