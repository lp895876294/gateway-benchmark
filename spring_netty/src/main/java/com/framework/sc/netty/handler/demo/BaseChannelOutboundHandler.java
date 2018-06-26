package com.framework.sc.netty.handler.demo;

import com.framework.sc.netty.handler.GWChannelHandler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import lombok.Getter;
import lombok.Setter;

@ChannelHandler.Sharable
@Setter
@Getter
public class BaseChannelOutboundHandler extends ChannelOutboundHandlerAdapter implements GWChannelHandler {

    public String name ;

    public BaseChannelOutboundHandler(){
        this.name = "out: "+this.getClass().getSimpleName() ;
    }

}
