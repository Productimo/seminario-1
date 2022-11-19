package com.seminario.dtos;

public class ResponseDTO {

	private Integer status;
	private String message;
	
	public ResponseDTO(Integer status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
