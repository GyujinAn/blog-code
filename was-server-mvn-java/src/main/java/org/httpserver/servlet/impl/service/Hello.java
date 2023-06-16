package org.httpserver.servlet.impl.service;

import org.httpserver.HttpServer;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;
import org.httpserver.servlet.HttpSimpleServlet;
import org.httpserver.servlet.impl.DefaultServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;

public class Hello extends HttpSimpleServlet {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);
    @Override
    protected void doGet(SimpleHttpRequest request, SimpleHttpResponse response) {
        logger.trace("doGet is started");

        OutputStream outputStream = response.getOutputStream();
        Writer out = new OutputStreamWriter(outputStream);
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String formattedTime = currentTime.format(formatter);


        try {
            String body = new StringBuilder("<HTML>\r\n")
                    .append("<HEAD><TITLE>CurrentTime</TITLE>\r\n")
                    .append("</HEAD>\r\n")
                    .append("<BODY>")
                    .append("service hello from servlet")
                    .append("</BODY>")
                    .append("</HTML>\r\n")
                    .toString();
            sendHeader(out, "HTTP/1.0 200 OK", "text/html; charset=utf-8", body.length());
            out.write(body);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            response.setStatusCode(SimpleHttpResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
