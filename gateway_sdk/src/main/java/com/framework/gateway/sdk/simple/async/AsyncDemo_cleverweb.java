package com.framework.gateway.sdk.simple.async;

import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.ApiCallBack;

public class AsyncDemo_cleverweb {

    private AsyncApiClient_cleverweb asyncClient = null;

    public AsyncDemo_cleverweb() {
        this.asyncClient = AsyncApiClient_cleverweb.newBuilder()
                .appKey("your app key here")
                .appSecret("your app secret here")
                .build();
    }

    public void aaaaaaDemo() {
        asyncClient.aaaaaa("default", new ApiCallBack() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                System.out.println("failure");
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                System.out.println("success");
                printResponse(response);
            }
        });
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

