package org.lyh.jcip.charpter02;

import net.jcip.annotations.NotThreadSafe;

import javax.servlet.*;
import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author lvyahui (lvyahui8@gmail.com,lvyahui8@126.com)
 * @since 2017/3/4 11:00
 */
@NotThreadSafe
public class CountingFactorizer implements Servlet{
    private final  AtomicLong count = new AtomicLong(0);

    public AtomicLong getCount() {
        return count;
    }

    public void init(ServletConfig servletConfig) throws ServletException {

    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        BigInteger i = extractFromRequest(servletRequest);
        BigInteger [] factors = factor(i);
        count.incrementAndGet();
        encodeIntoResponse(servletResponse,factors);
    }

    private void encodeIntoResponse(ServletResponse servletResponse, BigInteger[] factors) {

    }

    private BigInteger[] factor(BigInteger i) {
        return new BigInteger [] {i};
    }

    private BigInteger extractFromRequest(ServletRequest servletRequest) {
        return null;
    }


    public String getServletInfo() {
        return null;
    }

    public void destroy() {

    }
}
