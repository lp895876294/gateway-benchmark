package com.framework.gateway.sdk.simple;

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import com.alibaba.cloudapi.sdk.core.BaseApiClientBuilder;
import com.alibaba.cloudapi.sdk.core.annotation.NotThreadSafe;
import com.alibaba.cloudapi.sdk.core.annotation.ThreadSafe;
import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

@ThreadSafe
public final class SyncApiClient extends BaseApiClient {

    /**
     * 这里配置网关地址
     */
    public final static String GATEWAY_API_HOST = "localhost:8380";

    private SyncApiClient(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<SyncApiClient.Builder, SyncApiClient>{

        @Override
        protected SyncApiClient build(BuilderParams params) {
            return new SyncApiClient(params);
        }
    }

    public static Builder newBuilder(){
        return new SyncApiClient.Builder();
    }

    public static SyncApiClient getInstance(){
        return getApiClassInstance(SyncApiClient.class);
    }

    public ApiResponse apiId(String apiId) {
        String serverParamGetPath = "/api/server/param/get";

        ApiRequest apiRequest = new ApiRequest(Scheme.HTTP, Method.POST_BODY, GATEWAY_API_HOST, serverParamGetPath);

        apiRequest.addParam("apiId", apiId, ParamPosition.QUERY, true);

        return syncInvoke(apiRequest);
    }

}

