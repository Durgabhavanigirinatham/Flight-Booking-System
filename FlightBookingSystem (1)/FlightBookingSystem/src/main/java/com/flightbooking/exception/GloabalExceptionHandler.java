
package com.flightbooking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Flight;
@ControllerAdvice
public class GloabalExceptionHandler extends ResponseEntityExceptionHandler{
	@ExceptionHandler(FlightNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> FlightNotFoundHandler(FlightNotFoundException e){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> FlightNotFoundHandler(IdNotFoundException e){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(BookingNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> BookingNotFoundHandler(BookingNotFoundException e){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PassengersNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> PassengersNotFoundHandler(PassengersNotFoundException e){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> PaymentNotFoundHandler(PaymentNotFoundException e){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ContactNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> ContactNotFoundHandler(ContactNotFoundException e){
		ResponseStructure<String> response = new ResponseStructure<>();
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setMessage("Failure");
		response.setData(e.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}

}
