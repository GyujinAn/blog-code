package designpatterns.singleton01;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author agj017@gmail.com
 * @since 2022/04/16
 */
public class SingletonDataSource {

    private static SingletonDataSource uniqueInstance;

    static {
        try {
            uniqueInstance = new SingletonDataSource();
        } catch (FileNotFoundException e) {
            System.out.println("check your datasource.txt");
            e.printStackTrace();
        }
    }

    private BufferedReader reader;

    private Map<String, String> map;

    private SingletonDataSource() throws FileNotFoundException {
        reader = new BufferedReader(new FileReader("java/src/designpatterns/singleton01/datasource.txt"));
        map = new HashMap<>();
        init();
    }

    private void init(){
        String str;
        while (true) {
            try {
                if (!((str = reader.readLine()) != null)) break;
                System.out.println(str);
                String[] split = str.split("=");
                map.put(split[0],split[1]);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(map);

    }


    public static SingletonDataSource getInstance(){

        return uniqueInstance;
    }

    public String getUrl(){
        return map.get("url");
    }

    public String getUsername(){
        return map.get("username");
    }

    public String getPassword(){
        return map.get("password");
    }

}
