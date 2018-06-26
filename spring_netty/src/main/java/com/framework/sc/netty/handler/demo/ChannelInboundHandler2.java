package com.framework.sc.netty.handler.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(2)
public class ChannelInboundHandler2 extends BaseChannelInboundHandler {

    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        log.info("{} -> channelRead , msg={}" , name , msg.getClass().getName());

        ctx.fireChannelRead( msg ) ;
    }

}
