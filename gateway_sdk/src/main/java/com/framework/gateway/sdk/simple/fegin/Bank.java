package com.framework.gateway.sdk.simple.fegin;

import feign.Param;
import feign.RequestLine;

/**
 * @author toquery
 * @version 1
 */
public interface Bank {

    @RequestLine("GET /test/config")
    String contributors();
}
