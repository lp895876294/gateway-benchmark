
package com.alibaba.cloudapi.sdk.core.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * api同步调用响应类
 */

@Getter
@Setter
@ToString
public final class ApiResponse implements Serializable, Cloneable {

    private int statusCode;

    private String contentType;

    private String message;

    private Map<String, String> headers;

    private byte[] body;

}
