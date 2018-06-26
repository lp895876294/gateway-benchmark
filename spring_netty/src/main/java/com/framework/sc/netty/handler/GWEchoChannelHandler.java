package com.framework.sc.netty.handler;

import com.framework.common.util.UtilString;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.*;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

//@Component
@Slf4j
@ChannelHandler.Sharable
public class GWEchoChannelHandler extends ChannelInboundHandlerAdapter implements GWChannelHandler {
    private static final byte[] CONTENT = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r', 'l', 'd' };

    private static final String result = "{\"success\":\"haha\"}" ;

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
//        log.info("server -> read and flush"); ;
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        log.info("server -> read") ;
        if (!( msg instanceof HttpRequest )) {
//            log.error("{} , not a http request , {}" , JsonMapper.toJSONString( msg ) , msg.getClass().getName()) ;
            return ;
        }

        HttpRequest req = (HttpRequest) msg;

        FullHttpResponse response = null ;

        if(UtilString.endsWithAny( req.uri() , "favicon.ico" )){
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK , Unpooled.copiedBuffer(  "just favicon.ico", Charset.defaultCharset())) ;
        }else{
            response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK , Unpooled.wrappedBuffer( result.getBytes() ) ) ;
        }


        if (HttpUtil.is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE));
        }

        boolean keepAlive = HttpUtil.isKeepAlive(req);

        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain") ;
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes()) ;

        if (!keepAlive) {
//            log.info("server -> not keep alive");
            ctx.write(response).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) {
                    future.channel().close();
//                    log.info("server -> closed");
                }
            });
        } else {
//            log.info("server -> key alive");
            response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
            ctx.write(response);
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//        log.info("server -> exception");
        cause.printStackTrace();
        ctx.close();
    }

}
