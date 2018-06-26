package com.framework.sc.netty.handler.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
@Component
@Slf4j
@Order(1)
public class ChannelOutboundHandler1 extends BaseChannelOutboundHandler {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if ( !(msg instanceof HttpResponse) ) {
            return ;
        }

        log.info("{} -> write , msg={}" , name , msg.getClass().getName());
        super.write(ctx, msg, promise);
    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);

        log.info("{} -> close" , name);
    }
}
