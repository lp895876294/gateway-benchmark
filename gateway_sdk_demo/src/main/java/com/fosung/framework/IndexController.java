package com.fosung.framework;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.fosung.framework.demo.SyncApiClientDemo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author toquery
 * @version 1
 */
@RestController("index")
public class IndexController {


    private SyncApiClientDemo syncClient = SyncApiClientDemo.newBuilder()
            .appKey("123456")
            .appSecret("123456789")
            .build();

    @GetMapping("get")
    public String getSdk(@RequestParam(value = "", required = false, defaultValue = "1") String apiId) throws UnsupportedEncodingException {
        ApiResponse apiResponse = syncClient.index(apiId);
        return new String(apiResponse.getBody(), "utf-8");
    }

    @GetMapping("thread")
    public String thread(@RequestParam(value = "", required = false, defaultValue = "1") String apiId) throws UnsupportedEncodingException {
        ApiResponse apiResponse = syncClient.thread(apiId);
        return new String(apiResponse.getBody(), "utf-8");
    }

    @GetMapping("pool")
    public String pool(@RequestParam(value = "", required = false, defaultValue = "1") String apiId) throws UnsupportedEncodingException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 20; i++) {
            Runnable syncRunnable = () -> {
                syncClient.thread(apiId);
                System.out.println(Thread.currentThread().getName());
            };
            executorService.execute(syncRunnable);
        }
        return "utf-8";
    }
}
