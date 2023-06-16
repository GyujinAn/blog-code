package org.httpserver.servlet;

import org.httpserver.config.Config;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;


public interface SimpleServlet {

    void init(Config config);
    void service(SimpleHttpRequest req, SimpleHttpResponse res);

}
