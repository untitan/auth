package cn.t1tan.auth.service;

import cn.t1tan.auth.comm.AuthContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieBase;
import org.kie.api.runtime.StatelessKieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @description: 规则服务
 * @author: mengfs
 * @create: 2018-12-03
 **/
@Slf4j
@Data
@Component
public class RuleService {

	@Autowired
	private Map<String, KieBase> kieBaseMap;

	public StatelessKieSession getSession(String ruleName) {
		return kieBaseMap.get(ruleName + "KieBase").newStatelessKieSession();
	}

	public void execute(String ruleName, AuthContext context, Iterable objects) {
		StatelessKieSession statelessKieSession = this.getSession(ruleName);
		statelessKieSession.setGlobal("context", context);
		statelessKieSession.setGlobal("log", log);

		statelessKieSession.execute(objects);
	}
}
