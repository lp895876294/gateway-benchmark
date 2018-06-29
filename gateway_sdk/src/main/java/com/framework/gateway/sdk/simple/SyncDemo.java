package com.framework.gateway.sdk.simple;

import com.alibaba.cloudapi.sdk.core.model.ApiResponse;

public class SyncDemo {

    private SyncApiClient syncClient = null;

    public SyncDemo() {
        this.syncClient = SyncApiClient.newBuilder()
                .appKey("your app key here")
                .appSecret("your app secret here")
                .build();
    }

    public void aaaaaaDemo() {
        ApiResponse response = syncClient.apiId("1");
        printResponse(response);
    }

    private static void printResponse(ApiResponse response) {
        try {
            System.out.println("response code = " + response.getStatusCode());
            System.out.println("response content = " + new String(response.getBody(), "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

