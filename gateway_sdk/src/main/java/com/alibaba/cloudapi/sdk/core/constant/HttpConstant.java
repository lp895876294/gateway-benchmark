
package com.alibaba.cloudapi.sdk.core.constant;

/**
 * http协议相关常量
 */
public class HttpConstant {

    /**
     * 请求Header Accept
      */
    public static final String CLOUDAPI_HTTP_HEADER_ACCEPT = "Accept";
    /**
     * 请求Body内容MD5 Header
     */
    public static final String CLOUDAPI_HTTP_HEADER_CONTENT_MD5 = "Content-MD5";
    /**
     * 请求Header Content-Type
     */
    public static final String CLOUDAPI_HTTP_HEADER_CONTENT_TYPE = "Content-Type";
    /**
     * 请求Header UserAgent
     */
    public static final String CLOUDAPI_HTTP_HEADER_USER_AGENT = "User-Agent";
    /**
     * 请求Header Date
     */
    public static final String CLOUDAPI_HTTP_HEADER_DATE = "Date";
    /**
     * 请求Header Host
     */
    public static final String CLOUDAPI_HTTP_HEADER_HOST = "Host";

    /**
     * 表单类型Content-Type
     */
    public static final String CLOUDAPI_CONTENT_TYPE_FORM = "application/x-www-form-urlencoded; charset=UTF-8";
    /**
     * 流类型Content-Type
     */
    public static final String CLOUDAPI_CONTENT_TYPE_STREAM = "application/octet-stream; charset=UTF-8";
    /**
     * JSON类型Content-Type
     */
    public static final String CLOUDAPI_CONTENT_TYPE_JSON = "application/json; charset=UTF-8";
    /**
     * XML类型Content-Type
     */
    public static final String CLOUDAPI_CONTENT_TYPE_XML = "application/xml; charset=UTF-8";
    /**
     * 文本类型Content-Type
     */
    public static final String CLOUDAPI_CONTENT_TYPE_TEXT = "application/text; charset=UTF-8";
}
