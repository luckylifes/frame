package com.dj.cn.common;

/**
 * 
 * BaseResponse
 * 
 * @author: Lenovo
 * @date: 2018-11-09 12:02:00
 * @param <T>
 * @Copyright: 2018 www.lenovo.com Inc. All rights reserved.
 */
public class BaseResponse<T> {
	private boolean result = true;
	private String message;
	private T data;

	public BaseResponse() {
	}

	public BaseResponse(T data) {
		this.data = data;
	}

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setErrorMessage(String message) {
		this.result = false;
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}