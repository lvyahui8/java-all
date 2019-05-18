package org.lyh.jcip.charpter02;

import net.jcip.annotations.ThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/3/4 10:55
 */
@ThreadSafe
public class StatelessFactorizer implements Servlet {
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger [] factors = factor(i);
        encodeIntoResponse(servletResponse,factors);
    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] factors) {

    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger [] {i};
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return new BigInteger("8");
    }

    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
