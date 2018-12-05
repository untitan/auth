package cn.t1tan.auth.enums;

public enum AuthReason {
	S000(0, "00", AuthAction.Approve),
	R005(2, "05", AuthAction.Decline),
	R058(2, "58", AuthAction.Decline),
	R060(3, "60", AuthAction.Decline);

	private Integer priority;
	private String respCode;
	private AuthAction action;

	AuthReason(Integer priority, String respCode, AuthAction action) {
		this.priority = priority;
		this.respCode = respCode;
		this.action = action;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public String getRespCode() {
		return this.respCode;
	}

	public AuthAction getAction() {
		return this.action;
	}
}
