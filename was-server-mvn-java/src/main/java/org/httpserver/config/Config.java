package org.httpserver.config;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Config {

    public static final String ROOT_DIRECTORY = "src/main/resources/statics";
    private static final String CONFIG_PATH = "src/main/resources/config.json";
    private static Config config;

    public int port;

    public List<Host> hosts;

    public Map<String, String> mappings;

    private Config() {}

    public List<File> getDirectories() {
        List<File> directories = new ArrayList<File>();
        if (hosts == null || hosts.isEmpty()) {
            directories.add(new File(ROOT_DIRECTORY));
            return directories;
        }

        for (Host host : hosts) {
            String directory = host.directory;
            directories.add(new File(ROOT_DIRECTORY + "/" + directory));
        }
        return directories;
    }

    public static Config getConfigInstance(){
        if (config != null) {
            return config;
        }
        StringBuilder json = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Gson gson = new Gson();
        config = gson.fromJson(json.toString(), Config.class);
        return config;
    }


    public static void main(String[] args) {
        Config config = getConfigInstance();
        System.out.println();
    }
}

