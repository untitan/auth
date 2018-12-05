package cn.t1tan.auth.drools;

import cn.t1tan.auth.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @description: 规则重新加载
 * @author: mengfs
 * @create: 2018-12-04
 **/
@Slf4j
@Component
public class DroolsLoad {

	@Autowired
	private RuleService ruleService;

	@Autowired
	private DroolsUtil droolsUtil;

	public void reload() throws IOException {
		ruleService.setKieBaseMap(droolsUtil.loadRule());
	}

	public void clear() {
		ruleService.getKieBaseMap().clear();
	}
}
