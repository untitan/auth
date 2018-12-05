package cn.t1tan.auth.txn;

import cn.t1tan.auth.comm.AuthContext;

/**
 * 交易处理
 */
public interface ITxnHandler {

	public void handle(AuthContext context);
}
