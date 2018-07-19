package com.fosung.framework.demo;

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import com.alibaba.cloudapi.sdk.core.BaseApiClientBuilder;
import com.alibaba.cloudapi.sdk.core.annotation.NotThreadSafe;
import com.alibaba.cloudapi.sdk.core.annotation.ThreadSafe;
import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

@ThreadSafe
public final class SyncApiClientDemo extends BaseApiClient {

    /**
     * 这里配置网关地址
     */
    public final static String GATEWAY_API_HOST = "192.168.0.135:8081";

    private SyncApiClientDemo(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<SyncApiClientDemo.Builder, SyncApiClientDemo>{

        @Override
        protected SyncApiClientDemo build(BuilderParams params) {
            return new SyncApiClientDemo(params);
        }
    }

    public static Builder newBuilder(){
        return new SyncApiClientDemo.Builder();
    }

    public static SyncApiClientDemo getInstance(){
        return getApiClassInstance(SyncApiClientDemo.class);
    }

    public ApiResponse apiId(String apiId) {
        String serverParamGetPath = "/sign";
        ApiRequest apiRequest = new ApiRequest(Scheme.HTTP, Method.POST_BODY, GATEWAY_API_HOST, serverParamGetPath);
        apiRequest.addParam("apiId", apiId, ParamPosition.QUERY, true);
        return syncInvoke(apiRequest);
    }

    public ApiResponse index(String apiId) {
        String serverParamGetPath = "/index2/get";
        ApiRequest apiRequest = new ApiRequest(Scheme.HTTP, Method.POST_BODY, GATEWAY_API_HOST, serverParamGetPath);
        apiRequest.addParam("apiId", apiId, ParamPosition.QUERY, true);
        return syncInvoke(apiRequest);
    }

    public ApiResponse thread(String apiId) {
        String serverParamGetPath = "/index2/thread";
        ApiRequest apiRequest = new ApiRequest(Scheme.HTTP, Method.POST_BODY, GATEWAY_API_HOST, serverParamGetPath);
        apiRequest.addParam("apiId", apiId, ParamPosition.QUERY, true);
        apiRequest.addParam("time", "2000", ParamPosition.QUERY, true);
        return syncInvoke(apiRequest);
    }

}

