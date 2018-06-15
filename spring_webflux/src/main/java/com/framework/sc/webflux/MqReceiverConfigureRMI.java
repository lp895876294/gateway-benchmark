package com.framework.sc.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.jmx.support.ConnectorServerFactoryBean;
import org.springframework.remoting.rmi.RmiRegistryFactoryBean;
import org.springframework.util.StringUtils;

import java.net.InetAddress;

/**
 * JConsole 连接springboot
 * Created by lizhen on 2018\6\8 0008.
 */
@Slf4j
@Configuration
public class MqReceiverConfigureRMI implements EnvironmentAware , InitializingBean {

    private Environment environment ;

    private String rmiHost;

    private Integer rmiPort;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment ;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if( StringUtils.isEmpty( environment.getProperty( "jmx.rmi.hostname" ) ) ){
            try {
                rmiHost = InetAddress.getLocalHost().getHostAddress() ;
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            rmiHost = environment.getProperty( "jmx.rmi.hostname" ) ;
        }

        rmiPort = environment.getProperty( "jmx.rmi.port" , Integer.class , 6000 ) ;
    }

    @Bean
    public RmiRegistryFactoryBean rmiRegistry() {
        final RmiRegistryFactoryBean rmiRegistryFactoryBean = new RmiRegistryFactoryBean();
        rmiRegistryFactoryBean.setPort(rmiPort);
        rmiRegistryFactoryBean.setAlwaysCreate(true);
        return rmiRegistryFactoryBean;
    }

    @Bean
    @DependsOn("rmiRegistry")
    public ConnectorServerFactoryBean connectorServerFactoryBean() throws Exception {
        log.info("创建jmx远程调用, {}:{}" , rmiHost , rmiPort);
        final ConnectorServerFactoryBean connectorServerFactoryBean = new ConnectorServerFactoryBean();
        connectorServerFactoryBean.setObjectName("connector:name=rmi");
        connectorServerFactoryBean.setServiceUrl(String.format("service:jmx:rmi://%s:%s/jndi/rmi://%s:%s/jmxrmi", rmiHost, rmiPort, rmiHost, rmiPort));
        return connectorServerFactoryBean;
    }



}
