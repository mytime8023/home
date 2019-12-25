package cn.tedu.home.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.tedu.home.pojo.Admin;
import cn.tedu.home.service.AdminService;

public class EnceladusShiroRealm extends AuthorizingRealm{

	@Autowired
	private AdminService adminService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		String adminname = (String) principals.getPrimaryPrincipal();
		// 获取权限列表
		
		
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String adminname = (String) token.getPrincipal();
		
		Admin admin = adminService.findAdminByAdminname(adminname);
		
		if(admin == null) return null;
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(admin.getAdminname(), admin.getPassword(), getName());
		
		return authenticationInfo;
	}

}
