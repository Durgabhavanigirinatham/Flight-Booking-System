package com.flightbooking.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;
@Getter
@Setter
@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String airLine;
	private String source;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private Integer totalSeats;
	private double price;
	@JsonIgnore
	@OneToMany(mappedBy = "flight",cascade = CascadeType.ALL)
	private List<Booking> bookings;
	
	

}