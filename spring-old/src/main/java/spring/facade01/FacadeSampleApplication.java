package spring.facade01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import spring.Application;

@SpringBootApplication
public class FacadeSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
