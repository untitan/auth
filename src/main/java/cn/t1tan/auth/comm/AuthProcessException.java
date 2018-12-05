package cn.t1tan.auth.comm;

import cn.t1tan.auth.enums.AuthReason;
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

	private AuthReason authReason;

	public AuthProcessException(AuthReason reasonCode) {
		this.authReason = reasonCode;
	}

	public AuthProcessException(AuthReason reasonCode, String desc) {
		super(desc);
		this.authReason = reasonCode;
	}
}
