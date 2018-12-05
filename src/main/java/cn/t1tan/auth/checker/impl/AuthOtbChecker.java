package cn.t1tan.auth.checker.impl;

import cn.t1tan.auth.checker.IAuthChecker;
import cn.t1tan.auth.comm.AuthConstants;
import cn.t1tan.auth.comm.AuthContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 可用额度检查
 * @author: mengfs
 * @create: 2018-11-19
 **/
@Slf4j
@Service(AuthConstants.OtbChecker)
public class AuthOtbChecker implements IAuthChecker {
	@Override
	public void loadCheckData(AuthContext context) {
		log.info("加载[可用额度检查]数据");
	}
}
