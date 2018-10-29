package com.challenge.hotel.hospede;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

@RestController
public class hospedeController {


	@Autowired
	private hospedeService hospedeService;
	
	// Create a guest
	@PostMapping("/hospedes")
	public hospede createHospede(@Valid @RequestBody hospede hospede) {
		return hospedeService.createHospede(hospede);
	}
	
	// Read a guest
	@GetMapping("/hospedes/{hospedeId}")
	public hospede getHospede(@PathVariable Long hospedeId) {
		return hospedeService.getHospede(hospedeId);
	}
	
	// Update a guest	
	@PutMapping("/hospedes/{hospedeId}")
	public hospede updateHospede(@PathVariable Long hospedeId,
			@Valid @RequestBody hospede hospedeRequest) {
		return hospedeService.updateHospede(hospedeId, hospedeRequest);
	}
	
	// Delete a guest
	@DeleteMapping("/hospedes/{hospedeId}")
	public ResponseEntity<?> deleteHospede(@PathVariable Long hospedeId) {
		return hospedeService.deleteHospede(hospedeId);
	}
	
	// List guests
	@GetMapping("/hospedes")
	public Page<hospede> getHospedes(Pageable pageable) {
		return hospedeService.getHospedes(pageable);
	}
	
	// List guests by name
	@GetMapping("/hospedes/nome/{hospedeNome}")
	public List<hospede> getHospedesByNome(@PathVariable String hospedeNome) {
		return hospedeService.getHospedesByNome(hospedeNome);
	}
	
	// List guests by telefone
	@GetMapping("/hospedes/telefone/{hospedeTelefone}")
	public List<hospede> getHospedesByTelefone(@PathVariable Long hospedeTelefone) {
		return hospedeService.getHospedesByTelefone(hospedeTelefone);
	}
	
	// List guests in the hotel
/*	@GetMapping("/hospedes/nohotel")
	public List<hospede> getHospedesNoHotel() {
		return hospedeService.getHospedesNoHotel();
	}
	*/

}


