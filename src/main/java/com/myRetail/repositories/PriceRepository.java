package com.myRetail.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myRetail.models.Current_Price;

public interface PriceRepository extends MongoRepository<Current_Price, Long> {

	List<Current_Price> findAll();

	Optional<Current_Price> findById(long id);

}
