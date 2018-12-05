package cn.t1tan.auth.drools;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Map;

/**
 * @description: 规则配置
 * @author: mengfs
 * @create: 2018-11-22
 **/
@Slf4j
@Configuration
public class DroolsAutoConfig {

	@Autowired
	private DroolsUtil droolsUtil;

	@Bean
	public Map<String, KieBase> kieBases() throws IOException {
		return droolsUtil.loadRule();
	}
}
