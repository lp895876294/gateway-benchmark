package com.framework.sc.netty.handler.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
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

        log.info(ctx.channel().id().asLongText()+"-> write , msg={}" , msg.getClass().getName());
        super.write(ctx, msg, promise.addListener(new FutureListener<Object>() {
            @Override
            public void operationComplete(Future<Object> future) throws Exception {
                ctx.close() ;
            }
        }));


    }

    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
        log.info(ctx.channel().id().asLongText()+"-> close");
    }
}
