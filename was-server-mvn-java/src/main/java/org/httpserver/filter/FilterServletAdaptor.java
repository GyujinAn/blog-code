package org.httpserver.filter;

import org.httpserver.HttpServer;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;
import org.httpserver.servlet.SimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

public class FilterServletAdaptor implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(FilterServletAdaptor.class);

    SimpleServlet servlet;

    public FilterServletAdaptor(SimpleServlet servlet) {
        this.servlet = servlet;
    }

    @Override
    public void doFilter(SimpleHttpRequest request, SimpleHttpResponse response, FilterChain chain) {
        logger.trace("doFilter is started");
        servlet.service(request, response);
    }
}
