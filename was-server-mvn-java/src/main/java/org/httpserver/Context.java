package org.httpserver;


import org.httpserver.config.Config;
import org.httpserver.filter.ApplicationFilterChain;
import org.httpserver.http.SimpleHttpRequest;
import org.httpserver.http.SimpleHttpResponse;
import org.httpserver.servlet.SimpleServlet;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Context {

    private static final Logger logger = LoggerFactory.getLogger(Context.class);

    public final static String SERVLET_PKG = "org.httpserver.servlet";

    public final static String DEFAULT_SERVLET = "org.httpserver.servlet.impl.DefaultServlet";

    private final Map<String, SimpleServlet> container = new HashMap<>();

    private static Context context;

    private Config config;

    private MappingManager mappingManager;

    private Context(Config config) {
        this.config = config;
        createServlets();
        createMappingManager();
    }

    private void createServlets() {
        Reflections reflections = new Reflections(SERVLET_PKG, new SubTypesScanner(false));
        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        for (Class<?> clazz : classes) {
            String name = clazz.getName();
            String dir = name.split("\\.")[3];
            if (!dir.equals("impl")) {
                continue;
            }
            try {
                SimpleServlet servlet = (SimpleServlet) clazz.getDeclaredConstructor().newInstance();
                servlet.init(config);
                container.put(name, servlet);
                logger.info(name + " servlet has been created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void createMappingManager() {
        mappingManager = new MappingManager(config.mappings);
    }

    private ApplicationFilterChain createFilterChain(SimpleServlet servlet) {
        SimpleServlet servletToBeAdded;
        if (servlet == null) {
            servletToBeAdded = container.get(DEFAULT_SERVLET);
        } else {
            servletToBeAdded = servlet;
        }
        return new ApplicationFilterChain(servletToBeAdded);
    }

    public static Context getContext(){
        if (context != null) {
            return context;
        }
        context = new Context(Config.getConfigInstance());
        return context;
    }

    public void dispatch(SimpleHttpRequest request, SimpleHttpResponse response) {
        String endpoint = request.getEndpoint();
        SimpleServlet servlet;

        String key = mappingManager.mapping(endpoint);
        servlet = container.get(key);

        ApplicationFilterChain filterChain = createFilterChain(servlet);
        filterChain.doFilter(request, response);
    }

}
