package com.planetpayement.csv.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.planetpayement.csv.Message.ResponseMessage;



@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(MaxUploadSizeExceededException.class)
  public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
    return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
  }
  
  
  @ExceptionHandler(InvalidPhoneNumberException.class)
  public ResponseEntity<String> handleException(Exception ex) {
      // Customize the error message and HTTP status code based on the exception
      String errorMessage;
      HttpStatus status;
      
      if (ex instanceof InvalidPhoneNumberException) {
          errorMessage = "Invalid phone number: " + ex.getMessage();
          status = HttpStatus.BAD_REQUEST;
      } 
      else {
          errorMessage = "An error occurred: " + ex.getMessage();
          status = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      
      // Create the ResponseEntity with the error message and status
      return ResponseEntity.status(status).body(errorMessage);
  }
  
  
  
  
  
}