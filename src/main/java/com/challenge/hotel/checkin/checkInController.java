package com.challenge.hotel.checkin;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.challenge.hotel.hospede.hospede;

import javax.validation.Valid;
import java.util.List;


@RestController
public class checkInController {

	@Autowired
	private checkInService checkInService;

//	@Autowired
//	private hospedeService hospedeService;

	// Create a check-in
	@PostMapping("/hospedes/{hospedeId}/checkins")
	public checkIn addCheckIn(@PathVariable Long hospedeId,
			@Valid @RequestBody checkIn checkIn) {
		return checkInService.addCheckIn(hospedeId, checkIn);
	}
	
	// Read a check-in
	@GetMapping("/checkins/{checkInId}")
	public checkIn getCheckIn(@PathVariable Long checkInId) {
		return checkInService.getCheckIn(checkInId);
	}

	// Update a check-in
	@PutMapping("/hospedes/{hospedeId}/checkins/{checkInId}")
	public checkIn updateCheckIn(@PathVariable Long hospedeId,
			@PathVariable Long checkInId,
			@Valid @RequestBody checkIn checkInRequest) {
		return checkInService.updateCheckIn(hospedeId, checkInId, checkInRequest);
	}

	// Delete a check-in
	@DeleteMapping("/hospedes/{hospedeId}/checkins/{checkInId}")
	public ResponseEntity<?> deleteCheckIn(@PathVariable Long hospedeId,
			@PathVariable Long checkInId) {
		return checkInService.deleteCheckIn(hospedeId, checkInId);
	}
	
	// List check-ins from a guest
	@GetMapping("/hospedes/{hospedeId}/checkins")
	public List<checkIn> getCheckInsByHospedeId(@PathVariable Long hospedeId) {
		return checkInService.getCheckInsByHospedeId(hospedeId);
	}
	
	
	/*
	@GetMapping("/hospedes/QueSairamDoHotel")
	public List<hospede> getHospedesQueSairamDoHotel() {

		LocalDateTime agora = LocalDateTime.now();
		List<hospede> hospedesNoHotel = new ArrayList<hospede>();

		checkInRepository.findAll().forEach((checkIn) -> {
			if(agora.isAfter(checkIn.getDataSaida()) && agora.isAfter(checkIn.getDataEntrada())) {
				hospedesNoHotel.add(checkIn.getHospede());
			}

		});

		return hospedesNoHotel;
	}
	*/

}
