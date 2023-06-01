package org.httpserver.servlet;

import org.httpserver.config.Config;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;

import java.io.IOException;
import java.io.Writer;
import java.util.Date;


public class HttpSimpleServlet implements SimpleServlet{
    private static final String METHOD_DELETE = "DELETE";
    private static final String METHOD_HEAD = "HEAD";
    private static final String METHOD_GET = "GET";
    private static final String METHOD_OPTIONS = "OPTIONS";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_PUT = "PUT";
    private static final String METHOD_TRACE = "TRACE";

    protected Config config;

    @Override
    public void init(Config config) {
        this.config = config;
    }

    @Override
    public void service(SimpleHttpRequest request, SimpleHttpResponse response) {

        String method = request.getMethod();

        if (method.equals(METHOD_GET)) {
            doGet(request, response);

        } else {
            response.setStatusCode(SimpleHttpResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    protected void doGet(SimpleHttpRequest request, SimpleHttpResponse response) {
        response.setStatusCode(SimpleHttpResponse.SC_INTERNAL_SERVER_ERROR);
    }

    protected void sendHeader(Writer out, String responseCode, String contentType, int length)
            throws IOException {
        out.write(responseCode + "\r\n");
        Date now = new Date();
        out.write("Date: " + now + "\r\n");
        out.write("Server: JHTTP 2.0\r\n");
        out.write("Content-length: " + length + "\r\n");
        out.write("Content-type: " + contentType + "\r\n\r\n");
        out.flush();
    }

}
