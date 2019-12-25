/**
 * 
 */
package cn.tedu.home.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description: 拦截器配置
 * @author 成都彭于晏 2019年12月20日 下午2:44:00
 * @version 1.0
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer{

	@Autowired
	private PathInterceptor pi;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(pi).addPathPatterns("/**");
	}
	
}
