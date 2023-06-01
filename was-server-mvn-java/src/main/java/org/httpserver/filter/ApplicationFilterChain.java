package org.httpserver.filter;

import org.httpserver.config.Config;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;
import org.httpserver.servlet.SimpleServlet;

public class ApplicationFilterChain implements FilterChain{

    private Filter[] filters;

    private int pos;

    private static final int NUM_FILTERS = 3;

    public ApplicationFilterChain(SimpleServlet servlet) {
        this.filters = new Filter[NUM_FILTERS + 1];
        filters[0] = new ErrProcessFilter();
        filters[1] = new URLValidateFilter();
        filters[2] = new FilterServletAdaptor(servlet);
        pos = 0;
    }

    public void setServlet(SimpleServlet servlet) {
        if (servlet == null) {
            return;
        }
        filters[NUM_FILTERS - 1] = new FilterServletAdaptor(servlet);
    }

    @Override
    public void doFilter(SimpleHttpRequest request, SimpleHttpResponse response) {
        if (pos < NUM_FILTERS) {
            Filter filter = filters[pos++];
            filter.doFilter(request, response, this);
        }
    }
}
