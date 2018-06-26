package com.framework.sc.netty.handler.demo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(Ordered.LOWEST_PRECEDENCE )
public class ChannelEndPointHandler extends BaseChannelInboundHandler {

//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        if ( !(msg instanceof HttpRequest) ) {
//            return ;
//        }
//
//        log.info("{} -> channelRead , msg={}" , name , msg.getClass().getName());
//
//        HttpResponse httpResponse = new DefaultFullHttpResponse( HttpVersion.HTTP_1_1,
//                HttpResponseStatus.OK , Unpooled.wrappedBuffer( "demo".getBytes() ) ) ;
//        //将数据写到下一个ChannelHandler，需要在最后一个handler进行flush
//        ctx.write( httpResponse ).addListeners(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                //流程处理完成后回调监听
//                if( future.isSuccess() ){
//                    log.info("write success") ;
//                }else{
//                    log.info("write fail") ;
//                }
//                future.channel().close();
//                log.info("{} -> channelRead , 关闭channel。" , name);
//            }
//        }) ;
//
//        super.channelRead( ctx , msg ) ;
//    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        log.info("{} -> channelRead , msg={}" , name , msg.getClass().getName());

        HttpResponse httpResponse = new DefaultFullHttpResponse( HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK , Unpooled.wrappedBuffer( "{\"success\":true}".getBytes() ) ) ;
        //将数据写到下一个ChannelHandler，需要在最后一个handler进行flush
        ChannelFuture channelFuture = ctx.write( httpResponse ) ;

//        channelFuture.addListeners(new ChannelFutureListener() {
//            @Override
//            public void operationComplete(ChannelFuture future) throws Exception {
//                //流程处理完成后回调监听
//                if( future.isSuccess() ){
//                    log.info("write success") ;
//                }else{
//                    log.info("write fail") ;
//                    System.out.println("拦截outbound中所有异常 -> 开始打印异常信息");
//                    future.cause().printStackTrace();
//                }
//                future.channel().close();
//                log.info("{} -> channelRead , 关闭channel。" , name);
//            }
//        }) ;

    }

}
