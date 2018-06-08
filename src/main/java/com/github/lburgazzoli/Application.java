package com.github.lburgazzoli;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    public static class Routes extends RouteBuilder {
        @Override
        public void configure() {
            from("timer:start?period=1s")
                .serviceCall("_xmpp-server");
        }
    }
}
