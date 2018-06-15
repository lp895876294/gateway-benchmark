package com.framework.sc.webmvc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WebmvcApplication {

    public static void main(String[] args){
        new SpringApplicationBuilder( WebmvcApplication.class ).run() ;
    }

}
