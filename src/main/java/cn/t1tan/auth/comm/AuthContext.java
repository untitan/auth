package cn.t1tan.auth.comm;

import cn.t1tan.auth.enums.AuthReason;
import cn.t1tan.auth.enums.Channel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = false)
public class AuthContext extends AuthMessage {

	public AuthContext(Map<String, Object> message) {
		super(message);
	}

	private Channel channel;
	private Date txnDate;
	private String cardNo;
	private BigDecimal txnAmt;
	private String txnCode;
	private String txnType;
	private String txnDirection;
	private AuthReason finalAuthReason;
	private String finalRespCode;
	private String authCode;

	List<String> checkerList = new ArrayList<>();
	List<String> checkPointList = new ArrayList<>();

	Map<AuthReason, String> authReasonMap = new HashMap<>();

	public void putAuthReason(AuthReason reason) {
		this.finalAuthReason = reason;
		this.finalRespCode = reason.getRespCode();
	}
}
