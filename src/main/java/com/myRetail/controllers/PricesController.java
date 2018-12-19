package com.myRetail.controllers;

import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myRetail.exceptions.ProductException;
import com.myRetail.models.Current_Price;
import com.myRetail.repositories.PriceRepository;

@RestController
@RequestMapping("/")
public class PricesController {

	@Autowired
	private PriceRepository priceRepository;

	@GetMapping(value= "prices")
	public List<Current_Price> list(){
		return priceRepository.findAll();
		
	}
	
	@PostMapping(value= "prices")
	public Current_Price create(@RequestBody Current_Price current_Price) throws ProductException {
		if(priceRepository.findById(current_Price.getId()).isPresent()) {
			String message = MessageFormat.format("Current_Price for ProductDetails Id {0} already exists", current_Price.getId());
			throw new ProductException(message);
		}
		return priceRepository.save(current_Price);
		
	}
	
	@GetMapping(value= "prices/{id}")
	public Optional<Current_Price> get(@PathVariable Long id) throws ProductException{
		if(!priceRepository.findById(id).isPresent()) {
			String message = MessageFormat.format("Current_Price for ProductDetails Id {0} does not exist", id);
			throw new ProductException(message);
		}
		return priceRepository.findById(id);
		
	}
	
	@PutMapping(value= "prices/{id}")
	public Current_Price update(@PathVariable Long id, @RequestBody Current_Price current_Price) throws ProductException{
		Optional<Current_Price> existingPrice = priceRepository.findById(id);
		if(!get(id).isPresent()) {
			String message = MessageFormat.format("Current_Price for ProductDetails Id {0} does not exist", id);
			throw new ProductException(message);
		}
		
		BeanUtils.copyProperties(current_Price, existingPrice);
		return priceRepository.save(current_Price);
		
	}
	
	@DeleteMapping(value= "prices/{id}")
	public void delete(@PathVariable Long id) throws ProductException{
		if(!priceRepository.findById(id).isPresent()) {
			String message = MessageFormat.format("Current_Price for ProductDetails Id {0} does not exist", id);
			throw new ProductException(message);
		}
		priceRepository.deleteById(id);
	
	}
	
	@DeleteMapping(value= "prices")
	public void deleteAll(){
		priceRepository.deleteAll();
	
	}
}
