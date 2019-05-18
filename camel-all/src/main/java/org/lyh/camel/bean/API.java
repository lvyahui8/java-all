package org.lyh.camel.bean;


import org.lyh.camel.config.Method;
import org.lyh.camel.config.Produces;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/2/7 15:09
 */
public class API {

    private Method method;
    private String path;
    private String uri;
    private String produces = Produces.APPLICATION_JSON;

    private String realUrl;

    public Method getMethod() {
        return method;
    }

    public API setMethod(Method method) {
        this.method = method;
        return this;
    }

    public String getPath() {
        return path;
    }

    public API setPath(String path) {
        this.path = path;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public API setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getProduces() {
        return produces;
    }

    public API setProduces(String produces) {
        this.produces = produces;
        return this;
    }

    public String getRealUrl() {
        return realUrl;
    }

    public API setRealUrl(String realUrl) {
        this.realUrl = realUrl;
        return this;
    }
}
