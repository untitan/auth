package cn.t1tan.auth.txn.abs;

import cn.t1tan.auth.comm.AuthContext;
import cn.t1tan.auth.txn.ITxnHandler;

/**
 * @description: 正向交易的冲正处理
 * @author: mengfs
 * @create: 2018-11-26
 **/
public abstract class AbsTxnReverse implements ITxnHandler {

	@Override
	public void handle(AuthContext context) {
		this.findOrigAuth(context);
		this.checkOrigAuth(context);
		this.updateOrigAuthStatus(context);
		this.updateStatistics(context);
	}

	/**
	 * 查原交易
	 *
	 * @param context
	 */
	public abstract void findOrigAuth(AuthContext context);

	/**
	 * 检查原交易状态
	 *
	 * @param context
	 */
	public abstract void checkOrigAuth(AuthContext context);

	/**
	 * 更新原交易状态
	 *
	 * @param context
	 */
	public abstract void updateOrigAuthStatus(AuthContext context);

	/**
	 * 更新累计值
	 *
	 * @param context
	 */
	public abstract void updateStatistics(AuthContext context);
}
