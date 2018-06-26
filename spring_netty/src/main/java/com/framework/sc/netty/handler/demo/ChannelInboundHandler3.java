package com.framework.sc.netty.handler.demo;

import com.framework.sc.netty.handler.GWChannelHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE )
@ChannelHandler.Sharable
public class ChannelInboundHandler3 extends ChannelInboundHandlerAdapter implements GWChannelHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("{} -> channelRead , msg={}" , "ChannelInboundHandler3" , msg.getClass().getName());
        ctx.fireChannelRead(msg) ;
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        log.info("ChannelInboundHandler3 -> channelWritabilityChanged ");
    }
}
