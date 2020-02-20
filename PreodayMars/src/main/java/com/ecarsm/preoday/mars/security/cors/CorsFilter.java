package com.ecarsm.preoday.mars.security.cors;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class CorsFilter implements Filter {

    protected abstract String origin();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        resp.setHeader("Access-Control-Allow-Origin", origin());
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equals(req.getMethod())
                && origin().equals(req.getHeader("Origin"))) {

            resp.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
            resp.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, TenantID");
            resp.setHeader("Access-Control-Max-Age", "3600");

            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            chain.doFilter(request, response);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
