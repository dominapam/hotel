package com.challenge.hotel.hospede;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

	// List guests by phone
	@GetMapping("/hospedes/telefone/{hospedeTelefone}")
	public List<hospede> getHospedesByTelefone(@PathVariable Long hospedeTelefone) {
		return hospedeService.getHospedesByTelefone(hospedeTelefone);
	}

	// List guests by check-in Id
	@GetMapping("hospede/checkinId/{CheckinId}")
	public hospede getHospedeByCheckinId(@PathVariable Long CheckinId) {
		return hospedeService.getHospedeByCheckinId(CheckinId);
	}

	// List guests by check-in date
	@GetMapping("hospedes/checkinDataEntrada/{dataEntrada}")
	public List<hospede> getHospedeByDataEntrada(@PathVariable(value="dataEntrada")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dataEntrada) {
		return hospedeService.getHospedeByDataEntrada(dataEntrada);
	}

	// List guests by check-out date
	@GetMapping("hospedes/checkinDataSaida/{dataSaida}")
	public List<hospede> getHospedeByDataSaida(@PathVariable(value="dataSaida")
	@DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm:ss") LocalDateTime dataSaida) {
		return hospedeService.getHospedeByDataSaida(dataSaida);
	}

	// List guests in the hotel today
	@GetMapping("hospedes/noHotel")
	public List<hospede> getHospedesNoHotel() {
		return hospedeService.getHospedesNoHotel();
	}
	
	// List guests that checked-in in the hotel but already left
	@GetMapping("hospedes/saidosDoHotel")
	public List<hospede> getHospedesSaidos() {
		return hospedeService.getHospedesSaidos();
	}
}


