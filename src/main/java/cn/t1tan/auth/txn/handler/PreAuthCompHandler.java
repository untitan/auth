package cn.t1tan.auth.txn.handler;

import cn.t1tan.auth.comm.AuthContext;
import cn.t1tan.auth.txn.abs.AbsTxnReverse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 预授权完成
 * @author: mengfs
 * @create: 2018-11-26
 **/
@Slf4j
@Service
public class PreAuthCompHandler extends AbsTxnReverse {

	@Override
	public void findOrigAuth(AuthContext context) {
		log.info("预授权完成:查询原交易");
	}

	@Override
	public void checkOrigAuth(AuthContext context) {
		log.info("预授权完成:检查原交易");
	}

	@Override
	public void updateOrigAuthStatus(AuthContext context) {
		log.info("预授权完成:更新原交易状态");
	}

	@Override
	public void updateStatistics(AuthContext context) {
		log.info("预授权完成:更新累计值");
	}
}
