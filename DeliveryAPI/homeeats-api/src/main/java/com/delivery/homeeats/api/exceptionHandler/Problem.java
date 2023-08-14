package com.delivery.homeeats.api.exceptionHandler;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class Problem {
	
	private LocalDateTime dateTime;
	private String message;

}
