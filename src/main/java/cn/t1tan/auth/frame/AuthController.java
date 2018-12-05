package cn.t1tan.auth.frame;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @description: 入口
 * @author: mengfs
 * @create: 2018-11-22
 **/
@Slf4j
@RequestMapping("/")
@RestController
public class AuthController {

	@Autowired
	private AuthServiceFrame authServiceFrame;

	@PostMapping("/auth")
	public Map<String, Object> auth(@RequestParam Map<String, Object> requestMessage) {
		log.info("#################进入授权###################");

//		requestMessage.forEach((k, v) -> log.info("{}={}", k, v));
		log.info("请求报文:{}", requestMessage);

		Map<String, Object> response = authServiceFrame.execute(requestMessage);

		return response;
	}
}
