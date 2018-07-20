
package com.alibaba.cloudapi.sdk.core;

import com.alibaba.cloudapi.sdk.core.model.ApiCallBack;
import com.alibaba.cloudapi.sdk.core.model.ApiRequest;
import com.alibaba.cloudapi.sdk.core.model.ApiResponse;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.Future;

/**
 * httpclient interface
 */
public abstract class HttpClient implements Closeable {

    public HttpClient(BuilderParams builderParams) {
        init(builderParams);
    }

    protected abstract void init(BuilderParams builderParams);

    public abstract ApiResponse syncInvoke(ApiRequest request) throws IOException;

    public abstract Future<ApiResponse> asyncInvoke(ApiRequest request, ApiCallBack callback);

    public abstract void shutdown();

    @Override
    public void close() {
        shutdown();
    }
}
