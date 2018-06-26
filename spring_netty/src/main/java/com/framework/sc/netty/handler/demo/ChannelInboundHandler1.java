package com.framework.sc.netty.handler.demo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(1)
public class ChannelInboundHandler1 extends BaseChannelInboundHandler {

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        log.info( "channelRegistered -> " + ctx.channel().id().asLongText() );
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        super.channelUnregistered(ctx);
        log.info("channelUnregistered -> " + ctx.channel().id().asLongText() );
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("channelActive -> " + ctx.channel().id().asLongText() );
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.info("channelInactive -> " + ctx.channel().id().asLongText() );
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        super.channelReadComplete(ctx);
        log.info("channelReadComplete -> " + ctx.channel().id().asLongText() );
    }

    @Override
    public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
        super.channelWritabilityChanged(ctx);
        log.info("channelWritabilityChanged -> " + ctx.channel().id().asLongText() );
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, HttpRequest msg) throws Exception {
        HttpRequest httpRequest = (HttpRequest)msg ;
        log.info("uri->"+httpRequest.uri());

//        throw new IllegalArgumentException("aa00 -> "+name) ;

        log.info("{} -> channelRead , msg={}" , name , msg.getClass().getName());

        ctx.fireChannelRead( msg ) ;
    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
        log.info("handlerAdded -> " + ctx.channel().id().asLongText() );
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
        log.info("handlerRemoved -> " + ctx.channel().id().asLongText() );
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
//        log.info("exceptionCaught -> " + ctx.channel().id().asLongText() );
//        cause.printStackTrace();
//        ctx.close();
    }
}
