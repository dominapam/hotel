package com.challenge.hotel.model;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class valorEstadia {

	final float diariaSemana = 120;
	final float diariaFimdeSemana = 150;
	final float diariaGaragemSemana = 15;
	final float diariaGaragemFimdeSemana = 20;
	final LocalTime diariaExtraApos = LocalTime.of(16, 30);

	
	public float calculaValorEstadia(checkIn checkIn) {
		LocalDateTime saida = checkIn.getDataSaida();
		LocalDateTime entrada = checkIn.getDataEntrada();
		float valor =  0;

		LocalDateTime i = entrada;
		while (i.isBefore(saida)) {

			if (isFimdeSemana(entrada)) {
				valor =+ diariaFimdeSemana;
			} else {
				valor =+ diariaSemana;
			}

			if (saida.toLocalTime().isAfter(diariaExtraApos)) {
				if (isFimdeSemana(entrada)) {
					valor =+ diariaFimdeSemana;
				} else {
					valor =+ diariaSemana;
				}
			}
			i.plusDays(1);		
		}
		return valor;
	}

	public boolean isFimdeSemana(LocalDateTime dia) {
		return (dia.getDayOfWeek().equals(DayOfWeek.SUNDAY) || dia.getDayOfWeek().equals(DayOfWeek.SATURDAY));
	}


}
