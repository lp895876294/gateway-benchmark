
package com.alibaba.cloudapi.sdk.core.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import com.alibaba.cloudapi.sdk.core.BaseApiClient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 用于构建sdkClient的Builder所需的参数
 *
 */
@Getter
@Setter
@ToString
public final class BuilderParams implements Serializable, Cloneable {

    /**
     * 校验
     */
    private String appKey;
    private String appSecret;

    /**
     * 连接池 and dispatcher
     **/

    private int maxTotal = 10;
    private int defaultMaxPerRoute = 50;

    private long maxIdleTimeMillis = 60 * 1000L;
    private long keepAliveDurationMillis = 5000L;

    private Runnable idleCallback = null;
    private ExecutorService executorService = null;


    /**
     * timeout
     **/
    private long connectionTimeoutMillis = 15000L;
    private long readTimeoutMillis = 15000L;
    private long writeTimeoutMillis = 15000L;

    /**
     * https
     **/
    private SSLSocketFactory sslSocketFactory = null;
    private KeyManager[] keyManagers = null;
    private X509TrustManager[] x509TrustManagers = null;
    private SecureRandom secureRandom = null;
    private HostnameVerifier hostnameVerifier = null;

    /**
     * extra params
     */
    private Map<String, Object> extParams = new HashMap<String, Object>();

    private Class<? extends BaseApiClient> apiClientClass;

    public Object getExtParam(Object key) {return extParams.get(key);}

    public Object setExtParam(String key, Object value) {return extParams.put(key, value);}

    public boolean containsExtParam(Object key) {return extParams.containsKey(key);}

}
