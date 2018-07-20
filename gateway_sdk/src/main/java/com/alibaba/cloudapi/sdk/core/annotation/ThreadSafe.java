
package com.alibaba.cloudapi.sdk.core.annotation;

import java.lang.annotation.*;

/**
 * 用于标注某个类，表示其线程安全，可以在多线程场景下使用
 *
 * @see NotThreadSafe
 *
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface ThreadSafe {
}
