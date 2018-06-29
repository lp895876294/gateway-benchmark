package com.framework.gateway.sdk.simple.async;

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import com.alibaba.cloudapi.sdk.core.BaseApiClientBuilder;
import com.alibaba.cloudapi.sdk.core.annotation.NotThreadSafe;
import com.alibaba.cloudapi.sdk.core.annotation.ThreadSafe;
import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.core.model.ApiCallBack;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

@ThreadSafe
public final class AsyncApiClient_cleverweb extends BaseApiClient {
    public final static String GROUP_HOST = "4a0650cdb2d848c2ae8f5a5e62bc1281-cn-hangzhou.alicloudapi.com";

    private AsyncApiClient_cleverweb(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<AsyncApiClient_cleverweb.Builder, AsyncApiClient_cleverweb>{

        @Override
        protected AsyncApiClient_cleverweb build(BuilderParams params) {
            return new AsyncApiClient_cleverweb(params);
        }
    }

    public static Builder newBuilder(){
        return new AsyncApiClient_cleverweb.Builder();
    }

    public static AsyncApiClient_cleverweb getInstance(){
        return getApiClassInstance(AsyncApiClient_cleverweb.class);
    }

    public void aaaaaa(String userId, ApiCallBack _callBack) {
        String _apiPath = "/getUserInfo/[userId]";

        ApiRequest _apiRequest = new ApiRequest(Scheme.HTTP, Method.GET, GROUP_HOST, _apiPath);

        _apiRequest.addParam("userId", userId, ParamPosition.PATH, true);

        asyncInvoke(_apiRequest, _callBack);
    }

}

