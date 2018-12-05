package cn.t1tan.auth.drools;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description: 规则配置
 * @author: mengfs
 * @create: 2018-11-22
 **/
@Deprecated
@Slf4j
//@Configuration
public class DroolsTmpConfig {

	private static final String RULES_PATH = "rules";

	@Bean
	public KieBase cupKieBase() {
		KieHelper helper = new KieHelper();
		helper.addContent(this.getRuleTable(), ResourceType.DRL);
		return helper.build();
	}

	@Bean
	public KieBase txnCheckKieBase() throws IOException {
		KieHelper helper = new KieHelper();

		Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:" + RULES_PATH + "**/*.drl");
		for (Resource res : resources) {
			helper.addContent(this.getRuleDrl(res), ResourceType.DRL);
		}

		return helper.build();
	}

	private String getRuleDrl(Resource res) throws IOException {
		InputStream inputStream = ResourceFactory.newClassPathResource(RULES_PATH + File.separator + res.getFilename(), "UTF-8").getInputStream();
		String drl = IOUtils.toString(inputStream, "utf-8");
		log.info("drl:{}", drl);
		return drl;
	}

	private String getRuleTable() {
		//把excel翻译成drl文件
		SpreadsheetCompiler compiler = new SpreadsheetCompiler();
		String rules = compiler.compile(ResourceFactory.newClassPathResource(RULES_PATH + File.separator + "cup-rules.xlsx", "UTF-8"), "cup");
		log.info("xls:{}", rules);
		return rules;
	}
}
