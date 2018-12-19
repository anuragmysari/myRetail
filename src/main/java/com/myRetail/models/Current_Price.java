package com.myRetail.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
 
@Document(collection="price")
public class Current_Price {
	
	@Id
	@JsonProperty(access = Access.WRITE_ONLY)
	private long id;
	
	private double value;
	
	private String currencyCode;
	
	public Current_Price() {
		super();
	}
	
	public Current_Price(long id, double value, String currencyCode) {
		this.id = id;
		this.value = value;
		this.currencyCode = currencyCode;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}