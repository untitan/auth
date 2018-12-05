package cn.t1tan.auth.service;

import cn.t1tan.auth.checker.IAuthChecker;
import cn.t1tan.auth.comm.AuthConstants;
import cn.t1tan.auth.comm.AuthContext;
import cn.t1tan.auth.comm.AuthProcessException;
import cn.t1tan.auth.enums.AuthAction;
import cn.t1tan.auth.enums.AuthReason;
import cn.t1tan.auth.utils.AuthUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @description: 加载检查项
 * @author: mengfs
 * @create: 2018-11-19
 **/
@Slf4j
@Service
public class CheckService {

	@Autowired
	private Map<String, IAuthChecker> authCheckerMap;

	@Autowired
	private RuleService ruleService;

	/**
	 * 加载检查项
	 *
	 * @param context
	 */
	public void loadChecker(AuthContext context) {
		// todo 查询交易检查分类，根据交易码等获取需要的【检查服务】，将检查点做成list放在规则中执行（要求并行）
		// 使用交易码的交易分类(txnGroup)和客户标签(cust表的custTag)获取检查服务参数
		log.info("加载[检查服务列表编码]:{}", context.getTxnCode());

		// todo 此处应由参数获取
		context.getCheckerList().add(AuthConstants.NormalChecker);
		context.getCheckerList().add(AuthConstants.OtbChecker);

		log.info("加载到[检查服务]:{}", context.getCheckerList());

		// 加载检查类别的检查点
		for (String checker : context.getCheckerList()) {
			// 加载检查服务的业务数据
			authCheckerMap.get(checker).loadCheckData(context);

			// 加载检查服务的检查点
			List<String> checkPointList = this.loadCheckPointParam(checker);

			if (checkPointList != null) {
				context.getCheckPointList().addAll(checkPointList);
			}
		}

		// 去重
		List<String> newCheckPointList = context.getCheckPointList().stream().distinct().collect(Collectors.toList());
		context.setCheckPointList(newCheckPointList);

		// todo 没有命中，则使用默认检查项

		log.info("加载到[检查点]:{}", context.getCheckPointList());

	}

	/**
	 * 从参数获取检查服务的检查点
	 *
	 * @param checkerName
	 * @return
	 */
	private List<String> loadCheckPointParam(String checkerName) {
		// todo 从参数获取检查服务的检查点
		return Arrays.asList("Message_001", "Message_002", "Txn_001");
	}

	/**
	 * 预检查项
	 *
	 * @param context
	 */
	public void preCheck(AuthContext context) {
		//1、卡bin是否存在
		//2、卡号是否存在
		//3、账户是否存在
	}

	/**
	 * 交易检查
	 *
	 * @param context
	 */
	public void check(AuthContext context) {
		//log.info("执行规则检查");
		// 交易预检查、固定检查卡bin、卡号、账户是否存在
		ruleService.execute("txn", context, Arrays.asList(context, context.getMessage()));
		log.info("执行规则结果:{}", context.getAuthReasonMap());
	}

	/**
	 * 确定授权返回码
	 *
	 * @param context
	 */
	public void checkResult(AuthContext context) {
		//log.info("返回原因码检查");
		// 返回码检查并排序返回优先级最高的
		AuthReason reason = AuthReason.S000;

		if (MapUtils.isNotEmpty(context.getAuthReasonMap())) {
			// todo 规则中命中的原因码
			List<AuthReason> authReasons = context.getAuthReasonMap().keySet().stream().collect(Collectors.toList());
			Collections.sort(authReasons, Comparator.comparing(AuthReason::getPriority));
			reason = authReasons.get(0);
			log.info("规则结果排序:{}", authReasons);
		}

		context.putAuthReason(reason);
		log.info("交易检查最终结果:{},预设返回码:{}", context.getFinalAuthReason(), context.getFinalRespCode());

		if (context.getFinalAuthReason().getAction() == AuthAction.Approve) {
			log.info("交易检查通过");
		} else {
			AuthUtil.throwAuthException(AuthReason.R005, "交易检查拒绝");
		}
	}
}
