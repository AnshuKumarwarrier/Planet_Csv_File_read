package com.planetpayement.csv.Exception;

import org.springframework.http.ResponseEntity;

public class InvalidPhoneNumberException extends RuntimeException {
	
	public InvalidPhoneNumberException(ResponseEntity<String> responseEntity) {
		super();
	}
  
	
	public InvalidPhoneNumberException(String responseEntity) {
		super(responseEntity);
	}
    
}
