package com.delivery.homeeats.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	SYSTEM_ERROR("/system-erro", "System error"),
	INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
	MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
	RESOURCE_NOT_FOUND("/resource-not-found" , "Resource not found"),
	ENTITY_IN_USE("/entity-in-use", "Entity in use"),
	BUSINESS_ERROR("/business-erro", "Business rule violation");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://localhost:8080" + path;
		this.title = title;
	}

}
