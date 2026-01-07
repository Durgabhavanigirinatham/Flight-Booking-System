package com.flightbooking.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@Entity
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private LocalDate bookingDate;
	private String status;
	@OneToOne(mappedBy = "booking",cascade = CascadeType.ALL)
	private Payment payment;
	
	@JoinColumn
	@ManyToOne
	private Flight flight;
	
	@OneToMany(mappedBy = "booking",cascade = CascadeType.ALL)
	private List<Passenger> passengers;

}