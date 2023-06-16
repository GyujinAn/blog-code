package org.httpserver;

import java.util.HashMap;
import java.util.Map;

public class MappingManager {

    private Map<String, String> mappings;
    public MappingManager(Map<String, String> mappings) {
        this.mappings = new HashMap<>(mappings);
    }

    public String mapping(String endpoint) {
        String key;

        if (mappings.containsKey(endpoint)) {
            return mappings.get(endpoint);
        }

        if (endpoint.startsWith("/")) {
            endpoint = endpoint.substring(1);
        }
        if (!endpoint.isEmpty() && !endpoint.endsWith(".html") && !endpoint.endsWith(".htm")) {
            key = Context.SERVLET_PKG + ".impl." + endpoint;
            return key;
        }

        return null;

    }
}
