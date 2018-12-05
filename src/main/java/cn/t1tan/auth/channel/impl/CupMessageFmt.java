package cn.t1tan.auth.channel.impl;

import cn.t1tan.auth.channel.AuthMessageFmt;
import cn.t1tan.auth.comm.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @description: 银联报文检查
 * @author: mengfs
 * @create: 2018-11-22
 **/
@Slf4j
@Service(AuthConstants.CupMessageFmt)
public class CupMessageFmt implements AuthMessageFmt {

	@Override
	public void checkMessage() {
		log.info("检查银联报文");
	}
}
