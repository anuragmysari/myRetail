package com.myRetail.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Document(collection = "product")
public class Product {

	@Id
	private Long id;

	private String name;

	@JsonInclude(Include.NON_NULL)
	private Current_Price current_Price;

	public Product() {
		super();
	}

	public Product(Long id, String name, Current_Price current_Price) {
		this.id = id;
		this.name = name;
		this.current_Price = current_Price;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public Current_Price getCurrent_Price() {
		return current_Price;
	}

	public void setPrice(Current_Price current_Price) {
		this.current_Price = current_Price;
	}
}