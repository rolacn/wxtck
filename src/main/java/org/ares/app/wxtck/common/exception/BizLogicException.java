/**
 * Biz Logic Exception
 * @author ly
 */
package org.ares.app.wxtck.common.exception;

import static org.ares.app.wxtck.common.cfg.param.GlobalConfig.RC_BS_PROCESS_ERROR;

@SuppressWarnings("serial")
public class BizLogicException extends UDException {

	public BizLogicException() {
		super();
	}

	public BizLogicException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizLogicException(String message) {
		super(message);
	}

	public BizLogicException(Throwable cause) {
		super(cause);
	}
	
	public int getRetCode() {
		return retCode;
	}

	final static int retCode=RC_BS_PROCESS_ERROR;
}
