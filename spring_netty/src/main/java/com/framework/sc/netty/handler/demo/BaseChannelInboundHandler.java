package com.framework.sc.netty.handler.demo;

import com.framework.sc.netty.handler.GWChannelHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpRequest;
import lombok.Getter;
import lombok.Setter;

@ChannelHandler.Sharable
@Setter
@Getter
public abstract class BaseChannelInboundHandler extends SimpleChannelInboundHandler<HttpRequest> implements GWChannelHandler {

    public String name ;

    public BaseChannelInboundHandler(){
        this.name = "in : "+this.getClass().getSimpleName() ;
    }

}
