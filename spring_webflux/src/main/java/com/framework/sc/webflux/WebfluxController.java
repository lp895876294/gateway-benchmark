package com.framework.sc.webflux;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RestController
@RequestMapping("flux")
public class WebfluxController {

    private static AtomicInteger num = new AtomicInteger(0) ;

    @GetMapping("/{millSeconds}")
    public Mono<String> hello(@PathVariable long millSeconds) {
        log.info("request num = {}" , num.incrementAndGet()) ;

        return Mono.just( "{\"success\":true,\"delay\":"+millSeconds+"}"  ).delayElement( Duration.ofMillis( millSeconds ) ) ;
    }

}
