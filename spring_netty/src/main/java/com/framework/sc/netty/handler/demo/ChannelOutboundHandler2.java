package com.framework.sc.netty.handler.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(2)
public class ChannelOutboundHandler2 extends BaseChannelOutboundHandler {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        if ( !(msg instanceof HttpResponse) ) {
            return ;
        }
        log.info("{} -> write , msg={}" , name , msg.getClass().getName());
        super.write(ctx, msg, promise);
        //冲刷数据
        ctx.flush() ;

//        ctx.close() ;
    }
}
