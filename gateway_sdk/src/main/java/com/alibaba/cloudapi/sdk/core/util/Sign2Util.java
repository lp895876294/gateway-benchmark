
package com.alibaba.cloudapi.sdk.core.util;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;

import java.util.Collections;
import java.util.List;

/**
 * 签名工具类
 *
 * @author fred
 * @date 16/9/7
 */
@Slf4j
public class Sign2Util {

    /**
     * 签名值的连接符
     */
    private static final String SIGNATURE_VALUE_SPLITTER = ",";

    /**
     * 签名过滤的字符
     */
    private static final List<String> signatureEscapes = Lists.newArrayList(" ", "\\+");

    /**
     * 签名方法
     */
    public static String sign(String appKey, String appSecret, long signatureTime, String noce) {

        List<String> signatureValues = Lists.newArrayList(appKey, appSecret, signatureTime + "", noce);

        //过滤filter
        signatureValues = getFilteredValues(signatureValues);
        //对签名值进行排序
        sortSignatureValues(signatureValues);
        //获取签名文本
        String signatureText = getSignatureText(signatureValues);
        //加密后返回
        return encrypt(signatureText);

    }

    /**
     * 对签名文本进行加密
     */
    private static String encrypt(String signatureText) {
        //对签名文本进行2次md5加密
        String signature = DigestUtils.md5DigestAsHex(signatureText.getBytes());

        signature = DigestUtils.md5DigestAsHex(signature.getBytes());

        log.debug("签名文本加密后:{}", signature);

        return signature;
    }

    private static List<String> getFilteredValues(List<String> signatureValues) {
        List<String> filteredValues = Lists.newArrayListWithCapacity(signatureValues.size());
        for (String signatureValue : signatureValues) {
            //如果签名值为空，则不作为签名值的一部分
            if (StringUtils.isBlank(signatureValue)) {
                continue;
            }
            filteredValues.add(signatureValue);
        }
        return filteredValues;
    }

    /**
     * 对签名值进行排序
     */
    private static void sortSignatureValues(List<String> signatureValues) {
        //对values的值按照字母序进行正序排列
        Collections.sort(signatureValues);
    }

    /**
     * 获取需要进行签名的文本
     */
    private static String getSignatureText(List<String> signatureValues) {
        //获取签名参数
        String params = Joiner.on(SIGNATURE_VALUE_SPLITTER).join(signatureValues).toLowerCase();
        //过滤无效字符
        params = escapeSignatureValue(params);

        log.debug("签名文本:{}", params);

        //转换unicode
        String unicodeText = unicode(params);

        log.debug("签名文本unicode:{}", unicodeText);

        return unicodeText;
    }

    /**
     * 转换签名值，过滤特殊字符
     */
    private static String escapeSignatureValue(String signatureValue) {
        if (signatureValue == null) {
            return null;
        }
        //执行过滤
        for (String signatureEscape : signatureEscapes) {
            signatureValue = signatureValue.replaceAll(signatureEscape, "");
        }

        return signatureValue;
    }

    /**
     * 转换unicode
     */
    private static String unicode(String str) {
        int lenth = str.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenth; i++) {
            int code = str.charAt(i);
            String s = Integer.toHexString(code);
            sb.append("\\u");
            for (int j = s.length(); j < 4; j++) {
                sb.append(0);
            }
            sb.append(s);
        }
        return sb.toString();
    }


}
