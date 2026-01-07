package com.flightbooking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightbooking.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
	public Payment findByBooking_Id(int bookingId);
	public List<Payment> findByStatus(String status);
	public List<Payment> findByAmountGreaterThan(double amount);
	public List<Payment> findByMode(String mode);
}