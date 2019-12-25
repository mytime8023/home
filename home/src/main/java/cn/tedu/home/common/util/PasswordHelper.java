package cn.tedu.home.common.util;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;

import cn.tedu.home.pojo.Admin;

public class PasswordHelper {

	private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
	public static final String ALGORITHM_NAME = "md5"; // 基础散列算法
	public static final int HASH_ITERATIONS = 2; // 自定义散列次数
	
	public static void encryptPassword(Admin admin) {
		String password = new SimpleHash(ALGORITHM_NAME, admin.getPassword(), HASH_ITERATIONS).toHex();
		admin.setPassword(password);
	}
	
	public static void main(String[] args) {
		Admin admin = new Admin();
		admin.setPassword("123456");
		encryptPassword(admin);
		System.out.println(admin);
	}
}
