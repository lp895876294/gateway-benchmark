
package com.alibaba.cloudapi.sdk.core.exception;

/**
 * SDK客户端异常
 *
 */
public class SdkClientException extends RuntimeException {

    public SdkClientException(String message) {
        super(message);
    }

    public SdkClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public SdkClientException(Throwable cause) {
        super(cause);
    }

}
