package org.httpserver.servlet.impl;

import org.httpserver.HttpServer;
import org.httpserver.config.Config;
import org.httpserver.config.Host;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;
import org.httpserver.servlet.HttpSimpleServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.logging.Level;

public class DefaultServlet extends HttpSimpleServlet {


    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private static final String INDEX_FILE = "index.html";

    @Override
    protected void doGet(SimpleHttpRequest request, SimpleHttpResponse response) {
        logger.trace("doGet is started");
        Config config = super.config;
        Host host = null;
        String endpoint = request.getEndpoint();
        OutputStream outputStream = response.getOutputStream();
        Writer out = new OutputStreamWriter(outputStream);

        for (Host tmp : config.hosts) {
            if (tmp.name.equals(request.getHost())) {
                host = tmp;
                break;
            }
        }
        
        if (host == null) {
            host = Host.createDefaultHost();
        }

        if (endpoint.equals("/")) {
            endpoint = "/" + INDEX_FILE;
        }

        String filePath = Config.ROOT_DIRECTORY + "/" + host.directory + endpoint;
        File file = new File(filePath);

        if (!file.isFile() || !file.canRead()) {
            response.setStatusCode(SimpleHttpResponse.SC_NOT_FOUND);
            return;
        }

        try {
            String contentType =
                    URLConnection.getFileNameMap().getContentTypeFor(endpoint);
            byte[] theData = Files.readAllBytes(file.toPath());
            sendHeader(out, "HTTP/1.0 200 OK", contentType, theData.length);
            outputStream.write(theData);
            outputStream.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            response.setStatusCode(SimpleHttpResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
