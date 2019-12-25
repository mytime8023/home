package cn.tedu.home.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import cn.tedu.home.pojo.Admin;

/**
 * Description: 拦截器
 * @author 成都彭于晏 2019年12月20日 下午2:28:21
 * @version 1.0
 */

@Component
public class PathInterceptor implements HandlerInterceptor{

	/**
	 * 业务处理器处理请求之前被调用，对用户的request进行处理，若返回值为true，
	 * 则继续调用后续的拦截器和目标方法；若返回值为false，则终止请求
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// 判断当前被拦截的方法是否有注解
		boolean flag = handler.getClass().isAssignableFrom(HandlerMethod.class);
		if(flag) {
			// 获取当前方法上的InterceptorAnnotation注解
			PathInterceptorAnnotation ia = ((HandlerMethod) handler).getMethodAnnotation(PathInterceptorAnnotation.class);
			
			if(ia != null) {
				HttpSession session = request.getSession();
				Admin a = (Admin) session.getAttribute("login");
				
				if(a == null) {
					response.sendRedirect("/home/login");
					return false;
				}
			}
		}
		return true;
	}

	
}
