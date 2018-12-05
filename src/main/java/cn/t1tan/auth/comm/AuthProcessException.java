package cn.t1tan.auth.comm;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @description: 授权异常
 * @author: mengfs
 * @create: 2018-11-29
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class AuthProcessException extends RuntimeException {

	private String reasonCode;

	public AuthProcessException(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public AuthProcessException(String reasonCode, String desc) {
		super(desc);
		this.reasonCode = reasonCode;
	}
}
