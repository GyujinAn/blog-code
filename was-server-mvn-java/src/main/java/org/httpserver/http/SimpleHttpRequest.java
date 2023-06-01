package org.httpserver.http;

public class SimpleHttpRequest {

    private String method;

    private String endpoint;

    private String protocol;

    private String host;

    public void setMethod(String method) {
        this.method = method;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMethod() {
        return method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    @Override
    public String toString() {
        return "SimpleHttpRequest{" +
                "method='" + method + '\'' +
                ", endpoint='" + endpoint + '\'' +
                ", protocol='" + protocol + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
