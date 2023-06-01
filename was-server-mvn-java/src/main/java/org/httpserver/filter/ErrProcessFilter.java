package org.httpserver.filter;

import org.httpserver.HttpServer;
import org.httpserver.config.Config;
import org.httpserver.config.Host;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.Date;
import java.util.logging.Level;

public class ErrProcessFilter implements Filter{

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);


    @Override
    public void doFilter(SimpleHttpRequest request, SimpleHttpResponse response, FilterChain chain) {
        logger.trace("doFilter is started");

        chain.doFilter(request, response);

        Config config = Config.getConfigInstance();


        int statusCode = response.getStatusCode();
        Host host = null;

        for (Host tmp : config.hosts) {
            if (tmp.name.equals(request.getHost())) {
                host = tmp;
                break;
            }
        }

        if (host == null) {
            host = Host.createDefaultHost();
        }

        if (statusCode == SimpleHttpResponse.SC_OK) {
            return;
        }

        if (statusCode == SimpleHttpResponse.SC_FORBIDDEN) {
            String filePath = Config.ROOT_DIRECTORY + "/" + host.directory + "/" + host.fileName403;
            sendResponse(response, filePath);
        }

        if (statusCode == SimpleHttpResponse.SC_NOT_FOUND) {
            String filePath = Config.ROOT_DIRECTORY + "/" + host.directory + "/" + host.fileName404;
            sendResponse(response, filePath);
        }

        if (statusCode == SimpleHttpResponse.SC_INTERNAL_SERVER_ERROR) {
            String filePath = Config.ROOT_DIRECTORY + "/" + host.directory + "/" + host.fileName500;
            sendResponse(response, filePath);
        }
    }

    private void sendResponse(SimpleHttpResponse response, String path) {
        File file = new File(path);
        OutputStream outputStream = response.getOutputStream();
        Writer out = new OutputStreamWriter(outputStream);

        String code = Integer.toString(response.getStatusCode());
        String msg = response.getStatusCodeMsg();
        try {
            byte[] theData = Files.readAllBytes(file.toPath());
            sendHeader(out, "HTTP/1.0 " + code + " " + msg , "text/html; charset=utf-8", theData.length);
            outputStream.write(theData);
            outputStream.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            response.setStatusCode(SimpleHttpResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    private void sendHeader(Writer out, String responseCode, String contentType, int length)
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
