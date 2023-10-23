package com.oscarrdrgz.library.services.utils;

public class ServiceResponse<T> {
	private boolean status;
	private T data;
	public ServiceResponse(boolean status, T data) {
		super();
		this.status = status;
		this.data = data;
	}
	public ServiceResponse(boolean status) {
		super();
		this.status = status;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
