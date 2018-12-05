package cn.t1tan.auth.drools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 规则工具
 * @author: mengfs
 * @create: 2018-12-05
 **/
@Slf4j
@Component
public class DroolsUtil {

	private static final String RULES_PATH = "rules/";

	@Autowired
	private DroolsProperties droolsProperties;

	public Map<String, KieBase> loadRule() throws IOException {
		if (droolsProperties.getIsDb()) {
			return this.loadRuleFromDb();
		} else {
			return this.loadRuleFromFile();
		}
	}

	public Map<String, KieBase> loadRuleFromFile() throws IOException {
		Map<String, KieBase> kieBaseMap = new HashMap<>();

		List<Map<String, String>> ruleFiles = droolsProperties.getRuleFiles();
		for (Map<String, String> ruleFile : ruleFiles) {
			Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath*:" + RULES_PATH + ruleFile.get("value"));
			String rule = this.ruleFile2String(resource);
			KieHelper helper = new KieHelper();
			helper.addContent(rule, ResourceType.DRL);
			KieBase KieBase = helper.build();
			kieBaseMap.put(ruleFile.get("key"), KieBase);
			log.info("load-rule:{}", rule);
		}
		return kieBaseMap;
	}

	private String ruleFile2String(Resource resource) throws IOException {
		org.kie.api.io.Resource ruleResource = ResourceFactory.newClassPathResource(RULES_PATH + resource.getFilename(), "UTF-8");
		if (resource.getFilename().endsWith(".xlsx")) {
			return new SpreadsheetCompiler().compile(ruleResource, InputType.XLS);
		} else if (resource.getFilename().endsWith(".drl")) {
			return IOUtils.toString(ruleResource.getInputStream(), "utf-8");
		} else {
			return null;
		}
	}

	public Map<String, KieBase> loadRuleFromDb() {
		// todo 暂不实现
		return null;
	}
}
