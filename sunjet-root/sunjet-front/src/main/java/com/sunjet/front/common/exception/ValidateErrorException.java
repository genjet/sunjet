package com.sunjet.front.common.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.sunjet.front.common.vo.FailureVo;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ValidateErrorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private FailureVo failure;
	private List<FailureVo> failures;
	

	public ValidateErrorException(FailureVo failure) {
		super();
		this.failure = failure;
	}
	
	public FailureVo getFailure() {
		if(null == this.failure){
			this.failure = this.failures.get(0);
		}
		return this.failure;
	}
	
	public ValidateErrorException(List<FailureVo> failures) {
		super();
		this.failures = failures;
	}
	
	public List<FailureVo> getFailures() {
		return failures;
	}
}
