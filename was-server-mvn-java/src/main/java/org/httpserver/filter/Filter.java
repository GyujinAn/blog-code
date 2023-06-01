package org.httpserver.filter;

import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;

public interface Filter {
    void doFilter(SimpleHttpRequest request, SimpleHttpResponse response, FilterChain chain);
}
