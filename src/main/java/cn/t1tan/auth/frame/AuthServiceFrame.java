package cn.t1tan.auth.frame;

import cn.t1tan.auth.comm.AuthContext;
import cn.t1tan.auth.comm.AuthProcessException;
import cn.t1tan.auth.enums.Channel;
import cn.t1tan.auth.service.AuthService;
import cn.t1tan.auth.service.CheckService;
import cn.t1tan.auth.service.MessageService;
import cn.t1tan.auth.service.TxnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 1、 授权流水登记
 * 2、 授权请求格式检查
 * 3、 检查引擎（数据加载、交易识别）
 * 4、 RTDS侦测
 * 5、 v+服务
 * 6、 ISC信息交换
 * 7、 额度更新恢复
 * 8、 授权码生成
 * 9、 响应码管理
 * 10、授权流水更新
 */
@Slf4j
@Service("AuthServiceFrame")
public class AuthServiceFrame {

	@Autowired
	private MessageService messageService;

	@Autowired
	private AuthService authService;

	@Autowired
	private TxnService txnService;

	@Autowired
	private CheckService checkService;

	public Map<String, Object> execute(Map<String, Object> message) {
		AuthContext context = new AuthContext(message);

		try {
			// todo 暂定cup
			context.setChannel(Channel.Cup);

			log.info("# 1、授权流水登记");
			messageService.saveMessageLog();

			log.info("# 2、授权请求格式检查");
			messageService.checkMessageFormat(context);

			log.info("# 3、加载数据");
			authService.loadBizDate(context);
			authService.loadParam(context);
			authService.loadCustData(context);

			log.info("# 3、检查引擎-交易识别");
			txnService.txnIdentify(context);

			log.info("# 3、加载检查项");
			checkService.loadChecker(context);

			log.info("# 4、交易检查");
			checkService.check(context);

			log.info("# 5、规则结果处理");
			checkService.checkResult(context);

			log.info("# 7、交易处理");
			txnService.handle(context);

			log.info("# 9、授权码生成");
			authService.genAuthCode(context);

			log.info("# 10、授权流水更新");
			authService.saveAuthTxn(context);

			log.info("# 11、组装返回报文");
			messageService.genResponseMessage(context);

			//log.info("4、RTDS侦测");
			//log.info("5、v+服务");
			//log.info("6、ISC信息交换");
		} catch (AuthProcessException excetion) {
			log.warn("交易失败:{}-{}", excetion.getAuthReason(), excetion.getMessage());
			context.putAuthReason(excetion.getAuthReason());
			messageService.genResponseMessage(context);
		}

		return context.getMessage();
	}
}
