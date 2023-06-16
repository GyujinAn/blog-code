package org.httpserver;

import org.httpserver.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class HttpServer {

    private static final Logger logger = LoggerFactory.getLogger(HttpServer.class);

    private static final int NUM_THREADS = 50;

    private Config config;

    public HttpServer(Config config) throws IOException {
        setConfig(config);
    }

    private void setConfig(Config config) throws IOException {
        List<File> directories = config.getDirectories();
        for (File directory : directories) {
            logger.info("directory path: " + directory.getAbsolutePath());
            if (!directory.isDirectory()) {
                throw new IOException(directory
                        + " does not exist as a directory");
            }
        }
        this.config = config;
    }

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(config.port)) {
            logger.info("Accepting connections on port " + server.getLocalPort());
            List<File> directories = config.getDirectories();
            for (File directory : directories) {
                logger.info("Document Root: " + directory);
            }
            Context.getContext();
            while (true) {
                try {
                    Socket request = server.accept();
                    Runnable r = new RequestProcessor(request);
                    pool.submit(r);
                } catch (IOException ex) {
                    logger.error("Error accepting connection", ex);
                }
            }
        }
    }

    public static void main(String[] args) {
        Config config = Config.getConfigInstance();

        if (config.port < 0 || config.port > 65535) {
            config.port = 80;
        }

        try {
            HttpServer webserver = new HttpServer(config);
            webserver.start();
        } catch (IOException ex) {
            logger.error("Server could not start", ex);
        }

    }
}
