package cn.t1tan.auth.drools;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 规则参数配置
 * @author: mengfs
 * @create: 2018-12-04
 **/
@Data
@Component
@ConfigurationProperties(prefix = "drools")
public class DroolsProperties {
	private Boolean isDb;
	private List<Map<String, String>> ruleFiles = new ArrayList<>();
}
