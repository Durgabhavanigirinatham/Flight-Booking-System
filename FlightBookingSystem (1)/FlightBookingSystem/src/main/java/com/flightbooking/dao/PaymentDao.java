package com.flightbooking.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Payment;
import com.flightbooking.exception.BookingNotFoundException;
import com.flightbooking.exception.PaymentNotFoundException;
import com.flightbooking.repository.PaymentRepository;
@Repository
public class PaymentDao {
	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment savePayment(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	public List<Payment> fetchAllPayments(){
		return paymentRepository.findAll();
	}
	
	public Optional<Payment> fetchPaymentById(int id) {
		return paymentRepository.findById(id);
	}
	
	public List<Payment> fetchPaymentByStatus(String status){
		return paymentRepository.findByStatus(status);
	}
	
	public List<Payment> fetchPaymentAmountGreaterThan(double amount){
		return paymentRepository.findByAmountGreaterThan(amount);
	}
	
	public List<Payment> fetchPaymentByMode(String mode){
		return paymentRepository.findByMode(mode);
	}
	
	public org.springframework.data.domain.Page<Payment> fetchPaymentsByPaginationAndSorting(int pageNumber,int pageSize,String field){
		org.springframework.data.domain.Page<Payment>  payment = paymentRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
			if(!payment.isEmpty()) {
				return payment;
			}else {
				throw new PaymentNotFoundException("No Payment Found");
			}
		}
	
	

}