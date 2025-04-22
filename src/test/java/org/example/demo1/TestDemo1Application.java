package org.example.demo1;

import org.springframework.boot.SpringApplication;

public class TestDemo1Application {

    public static void main(String[] args) {
        SpringApplication.from(Demo1Application::main).with(TestcontainersConfiguration.class).run(args);
    }

}
