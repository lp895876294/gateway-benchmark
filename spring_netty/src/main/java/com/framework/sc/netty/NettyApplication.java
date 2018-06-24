package com.framework.sc.netty;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class NettyApplication {

    public static void main(String[] args){
        new SpringApplicationBuilder( NettyApplication.class ).run() ;
    }

}
