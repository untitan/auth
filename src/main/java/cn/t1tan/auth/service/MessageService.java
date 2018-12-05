package cn.t1tan.auth.service;

import cn.t1tan.auth.channel.AuthMessageFmt;
import cn.t1tan.auth.comm.AuthContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 报文服务
 */
@Slf4j
@Service
public class MessageService {

	@Autowired
	private Map<String, AuthMessageFmt> messageFmtMap;

	/**
	 * 保存报文
	 */
	public void saveMessageLog() {
		log.info("保存报文");
	}

	/**
	 * 检查报文格式
	 */
	public void checkMessageFormat(AuthContext context) {
		messageFmtMap.get(context.getChannel() + "MessageFmt").checkMessage();
	}

	/**
	 * 组装返回报文
	 *
	 * @param context
	 */
	public void genResponseMessage(AuthContext context) {
		String mti = String.format("%04d", Integer.valueOf(context.getMti()) + 10);
		context.getMessage().put("messageType", mti);
		context.getMessage().put("b039", context.getFinalRespCode());
		context.getMessage().put("b038", context.getAuthCode());
		log.info("返回码:{}", context.getFinalRespCode());
	}
}
