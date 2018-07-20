package com.framework.sc.netty.handler.demo;

import com.framework.sc.netty.handler.GWChannelHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(3)
@ChannelHandler.Sharable
public class ChannelInboundHandler3 extends ChannelInboundHandlerAdapter implements GWChannelHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(ctx.channel().id().asLongText()+"-> channelRead , msg={}" , msg.getClass().getName());
        ctx.fireChannelRead(msg) ;
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        log.info(ctx.channel().id().asLongText()+"-> channelWritabilityChanged ");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        super.exceptionCaught(ctx, cause);
        log.info(ctx.channel().id().asLongText()+"-> 拦截inbound中所有异常 -> " + ctx.channel().id().asLongText() );
        cause.printStackTrace();
        ctx.close();
    }
}
