package cn.t1tan.auth.utils;

import cn.t1tan.auth.comm.AuthProcessException;
import cn.t1tan.auth.enums.AuthReason;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: 授权工具
 * @author: mengfs
 * @create: 2018-12-04
 **/
@Slf4j
public class AuthUtil {

	/**
	 * 根据reason返回异常并记录日志
	 *
	 * @param reason
	 * @param desc
	 * @throws AuthProcessException
	 */
	public static void throwAuthException(AuthReason reason, String desc) throws AuthProcessException {
		throw new AuthProcessException(reason.name(), desc);
	}
}
