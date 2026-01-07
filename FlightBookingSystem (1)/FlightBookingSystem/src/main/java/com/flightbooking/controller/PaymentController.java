package com.flightbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Payment;
import com.flightbooking.service.PaymentService;

import lombok.Getter;
@RestController
@RequestMapping("/payment")
public class PaymentController {
	@Autowired
	private PaymentService paymentService;
	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>> savePayment(@RequestBody Payment payment){
		return paymentService.savePayment(payment);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Payment>>> fetchAllPayment(){
		return paymentService.fetchAllPayment();
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<ResponseStructure<Payment>> fetchPaymentById(@PathVariable int id){
		return paymentService.fetchPaymentById(id);
	}
	
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>> fetchPaymentByStatus(@PathVariable String status){
		return paymentService.fetchPaymentBySatus(status);
	}
	
	@GetMapping("/greaterthan/{amount}")
	public ResponseEntity<ResponseStructure<List<Payment>>> fetchPaymentByStatus(@PathVariable double amount){
		return paymentService.fetchPaymentAmountGreaterThan(amount);
	}
	
	@GetMapping("/mode/{mode}")
	public ResponseEntity<ResponseStructure<List<Payment>>> fetchPaymentByMode(@PathVariable String mode){
		return paymentService.fetchPaymentByMode(mode);
	}
	
	@PutMapping("/status/{bookingId}")
	public ResponseEntity<ResponseStructure<Payment>> updateBookingStatus(
	        @PathVariable int paymentId,
	        @RequestParam String status) {
	    return paymentService.updatePaymentStatus(paymentId, status);
	}
	
	@GetMapping("/pagination/{pageNumber}/{pageSize}/{field}")
    public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentsByPaginationAndSorting(
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String field) {

        return paymentService.fetchPaymentsByPaginationAndSorting(pageNumber, pageSize, field);
    }
	

}