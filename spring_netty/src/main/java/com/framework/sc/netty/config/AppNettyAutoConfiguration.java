package com.framework.sc.netty.config;

import com.framework.common.json.JsonMapper;
import com.framework.sc.netty.handler.GWChannelHandler;
import com.framework.sc.netty.handler.GWChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
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
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try {
            ServerBootstrap b = new ServerBootstrap();
            //服务器默认同时保持1024个sync状态的连接
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(appNettyProperties.getPort())
                    .childHandler(new GWChannelInitializer(this.gwChannelHandlers)) ;

            ChannelFuture channelFuture = b.bind().sync() ;

            log.info("netty server -> connection is ok");

            channelFuture.channel().closeFuture().sync() ;
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }


    }


}
