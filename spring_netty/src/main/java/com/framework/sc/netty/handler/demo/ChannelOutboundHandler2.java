package com.framework.sc.netty.handler.demo;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
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
        promise.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
//                System.out.println("打印异常信息->");
                if( !future.isSuccess() ){
                    future.cause().printStackTrace();
                }
//                ctx.writeAndFlush("{\"success\":false}") ;
            }
        }) ;
        log.info(ctx.channel().id().asLongText()+"-> write , msg={}" , msg.getClass().getName());
//        throw new IllegalArgumentException("outbound异常") ;
//        promise.setFailure(new IllegalArgumentException("outbound自定义异常")) ;
        super.write(ctx, msg, promise);
//        冲刷数据
        ctx.flush() ;
//        ctx.close() ;
    }

}
