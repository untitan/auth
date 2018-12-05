package cn.t1tan.auth.txn.abs;

import cn.t1tan.auth.comm.AuthContext;
import cn.t1tan.auth.txn.ITxnHandler;

/**
 * @description: 正常交易处理
 * @author: mengfs
 * @create: 2018-11-26
 **/
public abstract class AbsTxnNormal implements ITxnHandler {

	@Override
	public void handle(AuthContext context) {
		this.updateStatistics(context);
	}

	/**
	 * 更新累计值
	 *
	 * @param context
	 */
	public abstract void updateStatistics(AuthContext context);
}
