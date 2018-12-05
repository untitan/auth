package cn.t1tan.auth.service;

import cn.t1tan.auth.comm.AuthContext;
import cn.t1tan.auth.enums.AuthReason;
import cn.t1tan.auth.txn.ITxnHandler;
import cn.t1tan.auth.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;

/**
 * @description: 交易服务
 * @author: mengfs
 * @create: 2018-11-19
 **/
@Slf4j
@Service
public class TxnService {

	@Autowired
	private Map<String, ITxnHandler> txnHandlerMap;

	@Autowired
	private RuleService ruleService;

	/**
	 * 交易识别
	 */
	public void txnIdentify(AuthContext context) {
		log.info("调用规则引擎-决策表");

		ruleService.execute("cup", context, Arrays.asList(context, context.getMessage()));

		log.info("交易代码:{}", context.getTxnCode());
		log.info("交易类型:{}", context.getTxnType());
		log.info("交易方向:{}", context.getTxnDirection());

		if (StringUtils.isBlank(context.getTxnCode())) {
			AuthUtil.throwAuthException(AuthReason.R005, "交易代码为空");
		}
		if (!txnHandlerMap.containsKey(context.getTxnCode())) {
			AuthUtil.throwAuthException(AuthReason.R005, "交易代码处理器未找到");
		}
	}

	/**
	 * 根据交易码执行业务处理逻辑
	 *
	 * @param context
	 */
	public void handle(AuthContext context) {
		txnHandlerMap.get(context.getTxnCode()).handle(context);
	}
}
