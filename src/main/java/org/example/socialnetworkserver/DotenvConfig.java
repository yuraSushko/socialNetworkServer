package org.example.socialnetworkserver;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class DotenvConfig {

    @PostConstruct
    public void init() {
        Dotenv dotenv = Dotenv.load();
        System.out.println("DB_URL from .env: " + dotenv.get("DB_URL"));
    }
}
