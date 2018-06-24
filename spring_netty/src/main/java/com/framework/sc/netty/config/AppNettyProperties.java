package com.framework.sc.netty.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "server.netty")
@Setter
@Getter
public class AppNettyProperties {

	/**
	 * Server netty port.
	 */
	private Integer port = 8000 ;
	
	
}
