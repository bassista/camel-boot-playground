package com.github.lburgazzoli.hz;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.apache.camel.CamelContext;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.hazelcast.topic.HazelcastTopicComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        ConfigurableApplicationContext appContex = SpringApplication.run(Application.class, args);

        HazelcastInstance hz = appContex.getBean(HazelcastInstance.class);
        CamelContext context = appContex.getBean(CamelContext.class);
        HazelcastTopicComponent hzc = context.getComponent("hazelcast-topic", HazelcastTopicComponent.class);

        LOGGER.info("Component Instance = {}", hzc.getHazelcastInstance());
        LOGGER.info("Hazelcast Instance = {}", hz);
    }

    @Bean
    public HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance();
    }

    @Bean
    public RoutesBuilder routes() {
        return new RouteBuilder() {
            @Override
            public void configure() {
                from("direct:start")
                    .to("hazelcast-topic:entity-update");
            }
        };
    }
}
