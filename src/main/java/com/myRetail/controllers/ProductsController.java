package com.myRetail.controllers;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Optional;

import org.json.JSONException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myRetail.Utility;
import com.myRetail.exceptions.ProductException;
import com.myRetail.models.Current_Price;
import com.myRetail.models.Product;
import com.myRetail.repositories.PriceRepository;

@RestController
@RequestMapping("/")
public class ProductsController {

	@Autowired
	private PriceRepository priceRepository;

	@Value("${url}")
	String url;

	@GetMapping(value = "products/{id}")
	public Product get(@PathVariable Long id) throws ProductException, IOException, JSONException {

		String name = Utility.readNameFromExternalAPI(url + id);
		if (name.equals("") && !priceRepository.findById(id).isPresent()) {
			String message = MessageFormat.format("Product Id {0} does not exist", id.toString());
			throw new ProductException(message);
		}
		Current_Price p = priceRepository.findById(id).isPresent() ? priceRepository.findById(id).get() : null;

		return new Product(id, name, p);

	}

	@PutMapping(value = "products/{id}")
	public Product update(@PathVariable Long id, @RequestBody Product product)
			throws ProductException, IOException, JSONException {
		Optional<Current_Price> existingPrice = priceRepository.findById(id);
		if (!existingPrice.isPresent()) {
			String message = MessageFormat.format("Price with Product Id {0} does not exist", id.toString());
			throw new ProductException(message);
		}
		Current_Price p = new Current_Price(id, product.getCurrent_Price().getValue(),
				product.getCurrent_Price().getCurrencyCode());
		product.setName(Utility.readNameFromExternalAPI(url + id));
		BeanUtils.copyProperties(p, existingPrice);
		priceRepository.save(p);
		return product;

	}

}
