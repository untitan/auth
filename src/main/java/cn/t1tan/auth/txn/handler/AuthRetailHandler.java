package cn.t1tan.auth.txn.handler;

import cn.t1tan.auth.comm.AuthContext;
import cn.t1tan.auth.txn.abs.AbsTxnNormal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 消费
 * @author: mengfs
 * @create: 2018-11-26
 **/
@Slf4j
@Service
public class AuthRetailHandler extends AbsTxnNormal {

	@Override
	public void updateStatistics(AuthContext context) {
		log.info("消费：更新累计值");
	}
}
