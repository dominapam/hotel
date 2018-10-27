package com.challenge.hotel.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class pagamento {
	final double diariaSemana = 120;
	final double diariaFimdeSemana = 150;
	final double diariaGaragemSemana = 15;
	final double diariaGaragemFimdeSemana = 20;
	final LocalTime diariaExtraApos = LocalTime.of(16, 30);
	final int SATURDAY = 6;
	final int SUNDAY = 7;


	public double estadiaValor(checkIn checkIn) {
		LocalDateTime saida = checkIn.getDataSaida();
		LocalDateTime entrada = checkIn.getDataEntrada();
		double pagamento =  0;
		
		LocalDateTime i = entrada;
		while (i.isBefore(saida)) {
	
			if (isFimdeSemana(entrada)) {
				pagamento =+ diariaFimdeSemana;
			} else {
				pagamento =+ diariaSemana;
			}
			
			if (saida.toLocalTime().isAfter(diariaExtraApos)) {
				if (isFimdeSemana(entrada)) {
					pagamento =+ diariaFimdeSemana;
				} else {
					pagamento =+ diariaSemana;
				}
			}
			i.plusDays(1);		
		}
		return pagamento;
	}
	
	@SuppressWarnings("unlikely-arg-type")
	public boolean isFimdeSemana(LocalDateTime dia) {
		return (dia.getDayOfWeek().equals(SUNDAY) || dia.getDayOfWeek().equals(SATURDAY));
	}

	
	//public quantosDias 
	
	

}
