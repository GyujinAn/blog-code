package org.httpserver.filter;

import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;

public interface FilterChain {
    void doFilter(SimpleHttpRequest request, SimpleHttpResponse response);
}
