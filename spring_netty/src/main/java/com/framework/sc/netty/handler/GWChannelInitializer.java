package com.framework.sc.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

public class GWChannelInitializer extends ChannelInitializer<SocketChannel> {

    private List<GWChannelHandler> channelHandlers ;

    public GWChannelInitializer(List<GWChannelHandler> channelHandlers){
//        Assert.notEmpty( channelHandlers , "channelHandlers不能为空" );
        this.channelHandlers = channelHandlers ;
    }
        
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline p = ch.pipeline();

        p.addLast("codec", new HttpServerCodec()) ;

        channelHandlers.stream().forEach( channelHandler -> {
            p.addLast( channelHandler.getClass().getSimpleName() , channelHandler ) ;
        } );

    }
}