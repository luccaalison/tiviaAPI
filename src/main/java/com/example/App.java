package com.example;

import com.example.config.SQLiteInitializer; // Importando o SQLiteInitializer

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SQLiteInitializer.createDatabase(); 
        SpringApplication.run(App.class, args);
    }
}
