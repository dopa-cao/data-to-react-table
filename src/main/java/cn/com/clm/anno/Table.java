package cn.com.clm.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * describe: 标识table请求的类
 *
 * @author liming.cao@hand-china.com
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {

    String type() default "";

    String desc() default "";

}
