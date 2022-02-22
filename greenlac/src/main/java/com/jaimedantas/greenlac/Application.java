package com.jaimedantas.greenlac;

import com.jaimedantas.greenlac.configuration.Properties;
import com.jaimedantas.greenlac.state.SystemInfo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableConfigurationProperties(Properties.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SystemInfo systemInfo = new SystemInfo();
        SpringApplication.run(Application.class, args);
    }
}
