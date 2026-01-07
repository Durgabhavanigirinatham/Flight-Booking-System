package com.flightbooking.dto;

import lombok.*;

@Getter
@Setter
public class ResponseStructure<T> {
	private Integer status;
	private String message;
	private T data;
	
}