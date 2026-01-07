package com.flightbooking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.flightbooking.dao.PaymentDao;
import com.flightbooking.dto.ResponseStructure;
import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.Payment;
import com.flightbooking.exception.BookingNotFoundException;
import com.flightbooking.exception.FlightNotFoundException;
import com.flightbooking.exception.PaymentNotFoundException;
@Service
public class PaymentService {
	@Autowired
	private PaymentDao paymentDao;
	public ResponseEntity<ResponseStructure<Payment>> savePayment(Payment payment){
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatus(HttpStatus.CREATED.value());
		response.setMessage("payment added");
		response.setData(paymentDao.savePayment(payment));
		return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Payment>>> fetchAllPayment(){
		List<Payment> payments = paymentDao.fetchAllPayments();
		if(!payments.isEmpty()) {
			ResponseStructure<List<Payment>> response = new ResponseStructure<>();
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All payment fetched successfully");
			response.setData(payments);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("No payment found");
		}
		
	}
	

	public ResponseEntity<ResponseStructure<Payment>> fetchPaymentById(int id){
		Optional<Payment> payment = paymentDao.fetchPaymentById(id);
		if(!payment.isEmpty()) {
			ResponseStructure<Payment> response = new ResponseStructure<>();
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Payment fetched by id " + id);
			response.setData(payment.get());
			return new ResponseEntity<ResponseStructure<Payment>>(HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("No payment found");
		}
		
	}
	
	public ResponseEntity<ResponseStructure<List<Payment>>> fetchPaymentBySatus(String status){
		List<Payment> payments = paymentDao.fetchPaymentByStatus(status);
		if(!payments.isEmpty()) {
			ResponseStructure<List<Payment>> response = new ResponseStructure<>();
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All payments fetched successfully with status : "+status);
			response.setData(payments);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("No payment found");
		}
		
	}
	

	public ResponseEntity<ResponseStructure<List<Payment>>> fetchPaymentAmountGreaterThan(double amount){
		List<Payment> payments = paymentDao.fetchPaymentAmountGreaterThan(amount);
		if(!payments.isEmpty()) {
			ResponseStructure<List<Payment>> response = new ResponseStructure<>();
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Amount greater than "+amount+" payments");
			response.setData(payments);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("No payment found");
		}
		
	}
	
	public ResponseEntity<ResponseStructure<List<Payment>>> fetchPaymentByMode(String mode){
		List<Payment> payments = paymentDao.fetchPaymentByMode(mode);
		if(!payments.isEmpty()) {
			ResponseStructure<List<Payment>> response = new ResponseStructure<>();
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("All payments fetched successfully with the mode of transcation : "+ mode);
			response.setData(payments);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response,HttpStatus.OK);
		}else {
			throw new PaymentNotFoundException("No payment found");
		}
		
	}
	
	
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(Integer paymentId, String status) {
	    Optional<Payment> payment = paymentDao.fetchPaymentById(paymentId);
	    if (payment.isEmpty()) {
	        throw new PaymentNotFoundException("Payment with ID " + paymentId + " not found");
	    }

	    ResponseStructure<Payment> response = new ResponseStructure<>();
	    response.setStatus(HttpStatus.OK.value());
	    response.setMessage("Payment status updated successfully");
	    response.setData(paymentDao.savePayment(payment.get()));

	   return new ResponseEntity<ResponseStructure<Payment>>(response,HttpStatus.OK);
	}
	
	
	public ResponseEntity<ResponseStructure<Page<Payment>>> fetchPaymentsByPaginationAndSorting(
	        int pageNumber, int pageSize, String field) {

	    Page<Payment> page = paymentDao.fetchPaymentsByPaginationAndSorting(pageNumber, pageSize, field);

	    ResponseStructure<Page<Payment>> response = new ResponseStructure<>();
	    if (!page.isEmpty()) {
	        response.setStatus(HttpStatus.OK.value());
	        response.setMessage("Payments fetched successfully");
	        response.setData(page);
	        return new ResponseEntity<ResponseStructure<Page<Payment>>>(response, HttpStatus.OK);
	    } else {
	        throw new PaymentNotFoundException("No Payment found");
	    }
	
	
	}
	
	
	
}