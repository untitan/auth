package cn.t1tan.auth.comm;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * @description: 消息
 * @author: mengfs
 * @create: 2018-11-21
 **/
@Data
public class Message {

	public Message(Map<String, Object> message) {
		this.mti = (String) message.get("messageType");
		this.message = message;
	}

	private String mti;
	private Map<String, Object> message;

	public String field(String filed) {
		return (String) message.get(filed);
	}

	public String field(String filed, Integer start, Integer end) {
		return message.get(filed).toString().substring(start, end);
	}

	public boolean exist(String filed) {
		return message.containsKey(filed);
	}

	public void put(String filed, String value) {
		if (!StringUtils.isAnyBlank(filed, value)) {
			this.message.put(filed, value);
		}
	}
}
