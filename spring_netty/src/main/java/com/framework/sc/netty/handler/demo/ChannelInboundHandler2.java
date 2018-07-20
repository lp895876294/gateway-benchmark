package com.framework.sc.netty.handler.demo;

import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(2)
public class ChannelInboundHandler2 extends BaseChannelInboundHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(ctx.channel().id().asLongText()+"-> channelRead , msg={}" , msg.getClass().getName());

        ctx.fireChannelRead( msg ) ;
    }

}
