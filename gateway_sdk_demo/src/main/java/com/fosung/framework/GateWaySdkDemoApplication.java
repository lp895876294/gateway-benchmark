package com.fosung.framework;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author toquery
 * @version 1
 */
@SpringBootApplication
public class GateWaySdkDemoApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(GateWaySdkDemoApplication.class).run();
    }
}
