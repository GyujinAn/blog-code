package org.httpserver.config;

public class Host {
    public String name;
    public String directory;
    public String fileName403;
    public String fileName404;
    public String fileName500;

    public Host(String name, String directory, String fileName403, String fileName404, String fileName500) {
        this.name = name;
        this.directory = directory;
        this.fileName403 = fileName403;
        this.fileName404 = fileName404;
        this.fileName500 = fileName500;
    }

    public static Host createDefaultHost(){
        return new Host("localhost", "", "403.html", "404.html", "500.html");
    }
}
