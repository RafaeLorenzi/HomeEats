package com.delivery.homeeats.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {
	
	ENTITY_NOT_FOUND("/entity-not-found" , "Entity not found");
	
	private String title;
	private String uri;
	
	ProblemType(String path, String title) {
		this.uri = "https://localhost:8080" + path;
		this.title = title;
	}

}
