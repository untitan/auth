package cn.t1tan.auth.frame;

import cn.t1tan.auth.drools.DroolsLoad;
import cn.t1tan.auth.service.RuleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @description: 入口
 * @author: mengfs
 * @create: 2018-11-22
 **/
@Slf4j
@RequestMapping("/")
@RestController
public class DroolsController {

	@Autowired
	private RuleService ruleService;

	@Autowired
	private DroolsLoad droolsLoad;

	@GetMapping("/drools")
	public void drools() {
		log.info("drools:{}", ruleService.getKieBaseMap());
	}

	@GetMapping("/reload")
	public void reload() throws IOException {
		droolsLoad.reload();
		log.info("reload:{}", ruleService.getKieBaseMap());
	}

	@GetMapping("/clear")
	public void clear() {
		droolsLoad.clear();
		log.info("clear:{}", ruleService.getKieBaseMap());
	}
}
