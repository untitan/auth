package cn.t1tan.auth.service;

import cn.t1tan.auth.comm.AuthContext;
import cn.t1tan.auth.enums.AuthAction;
import cn.t1tan.auth.utils.AuthCodeGen;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description: 加载数据
 * @author: mengfs
 * @create: 2018-11-19
 **/
@Slf4j
@Service
public class AuthService {
	/**
	 * 加载业务日期
	 *
	 * @param context
	 */
	public void loadBizDate(AuthContext context) {
		context.setTxnDate(new Date());
	}

	/**
	 * 加载参数
	 *
	 * @param context
	 */
	public void loadParam(AuthContext context) {

	}

	/**
	 * 加载卡账客（主）
	 *
	 * @param context
	 */
	public void loadCustData(AuthContext context) {
		log.info("加载[卡账客]基础信息");
		this.loadCustom(context);
		this.loadAcct(context);
		this.loadCard(context);
	}

	/**
	 * 加载客户表
	 *
	 * @param context
	 */
	protected void loadCustom(AuthContext context) {
//		log.info("正在加载客户表");
	}

	/**
	 * 加载账户表
	 *
	 * @param context
	 */
	protected void loadAcct(AuthContext context) {
//		log.info("正在加载账户表");
	}

	/**
	 * 加载卡片表
	 *
	 * @param context
	 */
	protected void loadCard(AuthContext context) {
//		log.info("正在加载卡片表");
	}

	/**
	 * 生成授权码
	 *
	 * @param context
	 */
	public void genAuthCode(AuthContext context) {
		if (context.getFinalAuthReason().getAction() == AuthAction.Approve) {
			context.setAuthCode(AuthCodeGen.genAuthCode());
			log.info("授权码:{}", context.getAuthCode());
		}
	}

	/**
	 * 保存授权流水
	 *
	 * @param context
	 */
	public void saveAuthTxn(AuthContext context) {
		log.info("保存授权流水");
	}
}
