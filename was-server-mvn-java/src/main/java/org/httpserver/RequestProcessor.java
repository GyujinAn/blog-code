package org.httpserver;

import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;
import org.httpserver.util.SimpleHttpParser;

import java.net.Socket;
import java.util.logging.Logger;

public class RequestProcessor implements Runnable {
    private final Socket connection;
    private SimpleHttpParser parser;

    public RequestProcessor(Socket connection) {
        this.connection = connection;
        this.parser = new SimpleHttpParser(connection);
    }

    @Override
    public void run() {
        SimpleHttpRequest request = parser.parseHttpRequest();
        SimpleHttpResponse response = parser.parseHttpResponse();
        Context context = Context.getContext();
        context.dispatch(request, response);
    }

}
