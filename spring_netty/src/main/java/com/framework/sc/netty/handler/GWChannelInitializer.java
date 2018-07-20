package com.framework.sc.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
public class GWChannelInitializer extends ChannelInitializer<SocketChannel> {

    private List<GWChannelHandler> channelHandlers ;

    public GWChannelInitializer(List<GWChannelHandler> channelHandlers){
        Assert.notEmpty( channelHandlers , "channelHandlers不能为空" );
        this.channelHandlers = channelHandlers ;
        AnnotationAwareOrderComparator.sort( this.channelHandlers ) ;
        log.info("创建GWChannelInitializer");
    }
        
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        log.info("初始化ChannelPipeline");

        ChannelPipeline p = ch.pipeline();
        p.addLast("codec", new HttpServerCodec()) ;
        //将httpContent与Httprequest合并起来之后，一起传输
        p.addLast( new HttpObjectAggregator(8192) ) ;
        channelHandlers.stream().forEach( channelHandler -> {
            log.info("添加channel -> {}" , channelHandler.getClass().getSimpleName() );
            p.addLast( channelHandler.getClass().getSimpleName() , channelHandler ) ;
        } );

    }
}