package cn.t1tan.auth.txn.handler;

import cn.t1tan.auth.comm.AuthContext;
import cn.t1tan.auth.txn.abs.AbsTxnReverse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 消费撤销
 * @author: mengfs
 * @create: 2018-11-26
 **/
@Slf4j
@Service
public class AuthRetailVoidedHandler extends AbsTxnReverse {

	@Override
	public void findOrigAuth(AuthContext context) {
		log.info("消费撤销:查询原交易");
	}

	@Override
	public void checkOrigAuth(AuthContext context) {
		log.info("消费撤销:检查原交易");
	}

	@Override
	public void updateOrigAuthStatus(AuthContext context) {
		log.info("消费撤销:更新原交易状态");
	}

	@Override
	public void updateStatistics(AuthContext context) {
		log.info("消费撤销:更新累计值");
	}
}
