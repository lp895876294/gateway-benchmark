
package com.alibaba.cloudapi.sdk.core.model;

import com.alibaba.cloudapi.sdk.core.enums.Method;
import com.alibaba.cloudapi.sdk.core.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.core.enums.Scheme;
import com.alibaba.cloudapi.sdk.core.exception.SdkClientException;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.collections4.MapUtils;

/**
 * api请求类
 *
 */
@Getter
@Setter
@ToString
public final class ApiRequest implements Serializable, Cloneable {

    public ApiRequest(Scheme scheme, Method method, String host, String path) {
        this.scheme = scheme;
        this.method = method;
        this.host = host;
        this.path = path;
    }

    public ApiRequest(Scheme scheme, Method method, String host, String path, byte[] body) {
        this.scheme = scheme;
        this.method = method;
        this.host = host;
        this.path = path;
        this.body = body;
    }

    private Scheme scheme;

    private Method method;

    private String host;

    private String path;

    private Map<String, String> pathParams = new HashMap<String, String>();

    private Map<String, String> headers = new HashMap<String, String>();

    private Map<String, String> querys = new HashMap<String, String>();

    private Map<String, String> formParams = new HashMap<String, String>();

    private byte[] body;


    public void addParam(String name, Object value, ParamPosition position, boolean isRequired) {
        if (value == null) {
            if (isRequired) {
                throw new SdkClientException(String.format("参数 %s 不能为空, 请检查代码 。 ", name));
            } else {
                return;
            }
        }
        Map<String, String> targetParamMap = null;
        switch (position) {
            case HEADER: {
                targetParamMap = this.headers;
                break;
            }
            case PATH: {
                targetParamMap = this.pathParams;
                break;
            }
            case QUERY: {
                targetParamMap = this.querys;
                break;
            }
            case FORM: {
                targetParamMap = this.formParams;
                break;
            }
            default: {
                throw new SdkClientException("未知的参数类型 : " + position);
            }
        }
        if(value instanceof String){
            targetParamMap.put(name, (String)value);
        }else{
            targetParamMap.put(name, value.toString());
        }
    }

    public void addMappedParams(Map<String, String> params, ParamPosition position){
        if(MapUtils.isNotEmpty(params)){
            for(Entry<String, String> entry : params.entrySet()){
                addParam(entry.getKey(), entry.getValue(), position, false);
            }
        }
    }

    @Deprecated
    public void addPathParam(String name, String value) {
        this.pathParams.put(name, value);
    }

    @Deprecated
    public void addHeaderParam(String name, String value) {
        this.headers.put(name, value);
    }

    @Deprecated
    public void addQueryParam(String name, String value) {
        this.querys.put(name, value);
    }

    @Deprecated
    public void addFormParam(String name, String value) {
        this.formParams.put(name, value);
    }

}
