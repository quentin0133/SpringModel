package fr.dawan;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Log
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        log.info("Hello webhook!!!");
    }
}