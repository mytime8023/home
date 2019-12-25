package cn.tedu.home.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 拦截器注解，需要拦截的类或者方法上使用此注解
 * @author 成都彭于晏 2019年12月20日 下午2:29:28
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD}) // 作用于类、接口等与方法上
@Retention(value = RetentionPolicy.RUNTIME) // 运行时有效
public @interface PathInterceptorAnnotation {

	
}
