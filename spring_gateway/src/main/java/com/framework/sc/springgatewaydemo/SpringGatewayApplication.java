package com.framework.sc.springgatewaydemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class SpringGatewayApplication {

	@Autowired
	private Environment environment ;

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		String url = environment.getProperty("mvc.url" , "http://localhost:9000/" ) ;

		final String targetUrl = StringUtils.trimTrailingCharacter( url , '/' ) ;

		log.info("获取url:"+targetUrl);

		return builder.routes()
				.route("path_route", r -> r.path( "/mvc/1000" )
						.uri(targetUrl) )
				.route("path_route", r -> r.path( "/mvc/500" )
						.uri(targetUrl) )
				.build() ;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringGatewayApplication.class, args);
	}
}
