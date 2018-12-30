package cn.com.clm.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * describe: 标识table 的 header
 *
 * @author liming.cao@hand-china.com
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TableHeader {

    int index() default 0;

    String header() default "";

}
