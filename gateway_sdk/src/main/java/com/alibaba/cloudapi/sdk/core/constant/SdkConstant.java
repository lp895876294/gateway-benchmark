
package com.alibaba.cloudapi.sdk.core.constant;

import java.nio.charset.Charset;

/**
 * sdk相关常量
 */
public class SdkConstant {

    /**
     * 参与签名的系统Header前缀,只有指定前缀的Header才会参与到签名中
     */
    public static final String X_CA_HEADER_PREFIX = "X-Ca-";

    /**
     * APP KEY
     */
    public static final String X_CA_KEY = X_CA_HEADER_PREFIX + "Key";
    /**
     * 签名Header
     */
    public static final String X_CA_SIGNATURE = X_CA_HEADER_PREFIX + "Signature";

    /**
     * 签名认证类型 默认ACCESSKEY
     */
    public static final String X_CA_AUTH_TYPE = X_CA_HEADER_PREFIX + "Auth-Type";

    /**
     * 签名类型 MD5 SHA256
     */
    public static final String X_CA_SIGNATURE_TYPE = X_CA_HEADER_PREFIX + "Signature-Type";


    /**
     * 签名版本号
     */
    public static final String X_CA_VERSION = X_CA_HEADER_PREFIX + "Signature-Version";

    /**
     * 所有参与签名的Header
     */
    public static final String X_CA_SIGNATURE_HEADERS = X_CA_HEADER_PREFIX + "Signature-Headers";
    /**
     * 请求时间戳
     */
    public static final String X_CA_TIMESTAMP = X_CA_HEADER_PREFIX + "Timestamp";
    /**
     * 请求放重放Nonce,15分钟内保持唯一,建议使用UUID
     */
    public static final String X_CA_NONCE = X_CA_HEADER_PREFIX + "Nonce";


    /**
     * 编码UTF-8
     */
    public static final Charset CLOUDAPI_ENCODING = Charset.forName("UTF-8");

    /**
     * Header头的编码
     */
    public static final Charset CLOUDAPI_HEADER_ENCODING = Charset.forName("ISO-8859-1");

    /**
     * UserAgent
     */
    public static final String CLOUDAPI_USER_AGENT = "GATEWAY-JAVA-SDK;version=1";
    /**
     * 换行符
     */
    public static final String CLOUDAPI_LF = "\n";
    /**
     * 签名版本号
     */
    public static final String CLOUDAPI_CA_VERSION_VALUE = "1";
}
