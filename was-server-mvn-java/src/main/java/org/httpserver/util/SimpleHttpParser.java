package org.httpserver.util;

import org.httpserver.HttpServer;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SimpleHttpParser {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
    private Socket connection;

    public SimpleHttpParser(Socket connection) {
        this.connection = connection;
    }

    public SimpleHttpRequest parseHttpRequest() {
        SimpleHttpRequest request = new SimpleHttpRequest();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new BufferedInputStream(connection.getInputStream()), StandardCharsets.UTF_8));
            boolean isFirst = true;
            String line;
            while ((line = in.readLine()) != null && !line.isEmpty()) {
                if (isFirst) {
                    String[] tokens = line.split("\\s+");
                    request.setMethod(tokens[0].trim());
                    request.setEndpoint(tokens[1].trim());
                    request.setProtocol(tokens[2].trim());
                    isFirst = false;
                    continue;
                }
                if (line.startsWith("Host")) {
                    String[] tokens = line.split(":");
                    request.setHost(tokens[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            try {
                connection.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return request;
    }

    public SimpleHttpResponse parseHttpResponse() {
        SimpleHttpResponse response = null;
        try {
            OutputStream outputStream = new BufferedOutputStream(connection.getOutputStream());
            response = new SimpleHttpResponse(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage(), e);
            try {
                connection.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        return response;
    }
}
