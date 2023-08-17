package com.delivery.homeeats.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	
	INVALID_PARAMETER("/invalid-parameter", "Invalid parameter"),
	MESSAGE_NOT_READABLE("/message-not-readable", "Message not readable"),
	ENTITY_NOT_FOUND("/entity-not-found" , "Entity not found"),
	ENTITY_IN_USE("/entity-in-use", "Entity in use"),
	BUSINESS_ERROR("/business-erro", "Business rule violation");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://localhost:8080" + path;
		this.title = title;
	}

}
