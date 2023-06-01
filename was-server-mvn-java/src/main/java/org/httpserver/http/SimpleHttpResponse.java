package org.httpserver.http;

import java.io.OutputStream;

public class SimpleHttpResponse {

    public static final int SC_OK = 200;

    public static final int SC_FORBIDDEN = 403;

    public static final String SC_FORBIDDEN_MSG = "Forbidden";

    public static final int SC_NOT_FOUND = 404;

    public static final String SC_NOT_FOUND_MSG = "Not Found50";

    public static final int SC_INTERNAL_SERVER_ERROR = 500;

    public static final String SC_INTERNAL_SERVER_ERROR_MSG = "Internal Server Error";

    private int statusCode = SC_OK;

    private final OutputStream outputStream;

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public SimpleHttpResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public int getStatusCode() {
        return statusCode;
    }
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCodeMsg() {
        if (this.statusCode == SC_FORBIDDEN) {
            return SC_FORBIDDEN_MSG;
        } else if (this.statusCode == SC_NOT_FOUND) {
            return SC_NOT_FOUND_MSG;
        } else if (this.statusCode == SC_INTERNAL_SERVER_ERROR) {
            return SC_INTERNAL_SERVER_ERROR_MSG;
        }

        return "OK";
    }
}
