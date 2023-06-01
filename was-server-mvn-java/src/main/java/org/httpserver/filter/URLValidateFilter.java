package org.httpserver.filter;

import org.httpserver.HttpServer;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

public class URLValidateFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    @Override
    public void doFilter(SimpleHttpRequest request, SimpleHttpResponse response, FilterChain chain) {
        logger.trace("doFilter is started");
        String endpoint = request.getEndpoint();
        if (endpoint.contains("..")) {
            response.setStatusCode(SimpleHttpResponse.SC_FORBIDDEN);
            return;
         }
        if (endpoint.endsWith(".exe")) {
            response.setStatusCode(SimpleHttpResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(request, response);
    }
}
