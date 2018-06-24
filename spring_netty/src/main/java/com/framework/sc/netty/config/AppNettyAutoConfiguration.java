package com.framework.sc.netty.config;

import com.framework.common.json.JsonMapper;
import com.framework.sc.netty.handler.GWChannelHandler;
import com.framework.sc.netty.handler.GWChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
@EnableConfigurationProperties(AppNettyProperties.class)
public class AppNettyAutoConfiguration implements InitializingBean {

    private AppNettyProperties appNettyProperties ;

    private List<GWChannelHandler> gwChannelHandlers ;

    public AppNettyAutoConfiguration(AppNettyProperties appNettyProperties , ObjectProvider<List<GWChannelHandler>> gwChannelHandlerProviders){
        this.appNettyProperties = appNettyProperties ;

        this.gwChannelHandlers = gwChannelHandlerProviders.getIfAvailable() ;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("netty server -> init") ;

        log.info("netty server -> {}" , JsonMapper.toJSONString( appNettyProperties , true )) ;

        log.info("netty server -> 包含 {} 个 ChannelHandler" , this.gwChannelHandlers==null ? 0 : this.gwChannelHandlers.size() ) ;

        // Configure the server.
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(50);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new GWChannelInitializer(this.gwChannelHandlers)) ;

            Channel ch = b.bind( appNettyProperties.getPort() ).sync().channel() ;

            log.info("netty server -> connection is ok");

            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }


}
