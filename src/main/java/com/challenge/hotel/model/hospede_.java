package com.challenge.hotel.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2018-10-27T12:30:23.872-0300")
@StaticMetamodel(hospede.class)
public class hospede_ extends auditModel_ {
	public static volatile SingularAttribute<hospede, Long> id;
	public static volatile SingularAttribute<hospede, Integer> documento;
	public static volatile SingularAttribute<hospede, Integer> telefone;
	public static volatile SingularAttribute<hospede, String> nome;
}
