package com.framework.sc.netty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("mvc")
public class WebmvcController {

    private static AtomicInteger num = new AtomicInteger(0) ;

    @GetMapping("/{millSeconds}")
    public Object hello(@PathVariable long millSeconds) {
//        StopWatch stopWatch = new StopWatch() ;
//        stopWatch.start();
//        try {
//            TimeUnit.MILLISECONDS.sleep( millSeconds ) ;
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        stopWatch.stop();

        log.info("request num = {}" , num.incrementAndGet()) ;

        return "{\"success\":true,\"delay\":"+millSeconds+"}"  ;
    }

}
