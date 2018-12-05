package cn.t1tan.auth.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @description: 授权吗生成
 * @author: mengfs
 * @create: 2018-11-29
 **/
@Slf4j
public class AuthCodeGen {

	public static String genAuthCode() {
		String authCode = StringUtils.right(System.currentTimeMillis() + "", 6);
		log.info("交易通过-生成授权码:{}", authCode);
		return authCode;
	}
}
