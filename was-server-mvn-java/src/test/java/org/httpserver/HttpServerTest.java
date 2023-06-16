package org.httpserver;

import org.httpserver.config.Config;
import org.httpserver.config.Host;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HttpServerTest {

    @BeforeClass
    public static void startServer() {
        // Create a separate thread for running the HttpServer
        Thread serverThread = new Thread(() -> {
            try {
                Config config = Config.getConfigInstance();
                new HttpServer(config).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        serverThread.start();
    }

    @Test
    public void parseHostHeaderFromHelloServerTest() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://hello.com"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 200);
        assertTrue(responseBody.contains("Welcome simple hello was"));
    }

    @Test
    public void parseHostHeaderFromWorldServerTest() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://world.com"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 200);
        assertTrue(responseBody.contains("Welcome simple world was"));
    }

    @Test
    public void checkConfigTest() {

        Config config = Config.getConfigInstance();

        assertEquals(80, config.port);
        assertEquals(2, config.hosts.size());
        assertEquals(2, config.mappings.size());
        Host host = config.hosts.get(0);
        assertEquals("hello.com", host.name);
        assertEquals("hello", host.directory);
        assertEquals("hello403.html", host.fileName403);
        assertEquals("hello404.html", host.fileName404);
        assertEquals("hello500.html", host.fileName500);
    }

    @Test
    public void check403Test1() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/hello.exe"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 403);
        assertTrue(responseBody.contains("403"));
    }

    @Test
    public void check403Test2() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/../../etc/"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 403);
        assertTrue(responseBody.contains("403"));
    }

    @Test
    public void check404Test() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/goodbye.html"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 404);
        assertTrue(responseBody.contains("404"));
    }

    @Test
    public void check500Test() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/"))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 500);
        assertTrue(responseBody.contains("500"));
    }

    @Test
    public void requestHelloTest() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/Hello"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 200);
        assertTrue(responseBody.contains("hello from servlet"));
    }

    @Test
    public void requestServiceHelloTest() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/service.Hello"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 200);
        assertTrue(responseBody.contains("service hello from servlet"));
    }

    @Test
    public void requestGreetingTest() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/Greeting"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 200);
        assertTrue(responseBody.contains("hello from servlet"));
    }

    @Test
    public void requestSuperGreetingTest() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost/super.Greeting"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Get the response status code and body
        int statusCode = response.statusCode();
        String responseBody = response.body();

        // assert the response
        assertEquals(statusCode, 200);
        assertTrue(responseBody.contains("service hello from servlet"));
    }




}
