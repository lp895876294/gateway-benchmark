
package com.alibaba.cloudapi.sdk.core.http;

import java.lang.reflect.Constructor;

import com.alibaba.cloudapi.sdk.core.HttpClient;
import com.alibaba.cloudapi.sdk.core.exception.SdkClientException;
import com.alibaba.cloudapi.sdk.core.model.BuilderParams;

import org.apache.commons.lang3.StringUtils;

/**
 */
public class HttpClientFactory {

    public static String HTTP_CLIENT_IMPL_KEY = "aliyun.sdk.httpclient";
    public static String DEFAULT_HTTP_CLIENT = "com.alibaba.cloudapi.sdk.core.http.ApacheHttpClient";

    public static HttpClient buildClient(BuilderParams builderParams) {
        try {
            String httpClientClassName = System.getProperty(HTTP_CLIENT_IMPL_KEY);
            if (StringUtils.isEmpty(httpClientClassName)) {
                httpClientClassName = DEFAULT_HTTP_CLIENT;
            }
            Class httpClientClass = Class.forName(httpClientClassName);
            if (!HttpClient.class.isAssignableFrom(httpClientClass)) {
                throw new IllegalStateException(String.format("%s is not assignable from com.alibaba.cloudapi.sdk.core.HttpClient", httpClientClassName));
            }
            Constructor<? extends HttpClient> constructor = httpClientClass.getConstructor(BuilderParams.class);
            return constructor.newInstance(builderParams);
        } catch (Exception e) {
            throw new SdkClientException("HttpClientFactory buildClient failed", e);
        }
    }
}
