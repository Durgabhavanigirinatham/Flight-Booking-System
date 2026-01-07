package com.flightbooking.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.flightbooking.entity.Booking;
import com.flightbooking.entity.Passenger;
import com.flightbooking.entity.Payment;
import com.flightbooking.exception.BookingNotFoundException;
import com.flightbooking.repository.BookingRepository;
import com.flightbooking.repository.PassengerRepository;
import com.flightbooking.repository.PaymentRepository;
@Repository
public class BookingDao {
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private PassengerRepository passengerRepository; 
	
	@Autowired
	private  PaymentRepository paymentRepository;
	
	public Booking SaveBooking(Booking booking ) {
		return bookingRepository.save(booking);
	}
	
	public List<Booking> getAllBookings(){
		return bookingRepository.findAll();
	}
	
	public Optional<Booking> getBookingById(int id) {
		return bookingRepository.findById(id);
	}
	
	public List<Booking> getBookingsByFlightId(int id){
		return bookingRepository.findByFlight_Id(id);
	}
	
	public List<Booking> getBookingByDate(LocalDate bookingDate){
		return bookingRepository.findByBookingDate(bookingDate);
	}
	
	public List<Booking> getBookingsByStatus(String status){
		return bookingRepository.findByStatus(status);
	}
	
	public List<Passenger> getPassengersInBooking(int bookingId){
		return passengerRepository.findByBooking_Id(bookingId);
	}
	
	public Payment getPaymentDetails(int bookingId) {
		return paymentRepository.findByBooking_Id(bookingId);
	}
	
	public void deleteBookingStatus(Booking booking) {
		bookingRepository.delete(booking);
	}
	
	public org.springframework.data.domain.Page<Booking> fetchBookingsByPaginationAndSorting(int pageNumber,int pageSize,String field){
		org.springframework.data.domain.Page<Booking>  booking = bookingRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).descending()));
			if(!booking.isEmpty()) {
				return booking;
			}else {
				throw new BookingNotFoundException("No Booking Found");
			}
		}

}