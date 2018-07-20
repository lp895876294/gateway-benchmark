package com.framework.sc.netty.handler.demo;

import com.framework.sc.netty.handler.GWChannelHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.Getter;
import lombok.Setter;

@ChannelHandler.Sharable
@Setter
@Getter
public abstract class BaseChannelInboundHandler extends ChannelInboundHandlerAdapter implements GWChannelHandler {

    public String name ;

    public BaseChannelInboundHandler(){
        this.name = "in : "+this.getClass().getSimpleName() ;
    }

}
