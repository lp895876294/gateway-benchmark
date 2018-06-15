package com.framework.sc.webflux;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class WebfluxApplication {

    public static void main(String[] args){
        new SpringApplicationBuilder( WebfluxApplication.class ).run() ;

    }

}
