package com.tapiax.samples.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapiax.samples.model.CustomerLocation;
import com.tapiax.samples.repository.CustomerLocationRepository;

@RestController
public class CustomerLocationController {
	
	@Autowired
	private CustomerLocationRepository repository;
	
	@GetMapping("/customer-location/{page}/{size}")
	public List<CustomerLocation> getCustomerLocation(@PathVariable int page, @PathVariable int size){
		Page<CustomerLocation> pageResult = repository.findAll(PageRequest.of(page, size));
		return pageResult.toList();
	}
	
	@PostMapping("/customer-location")
	public ResponseEntity<CustomerLocation> createCustomerLocation(@RequestBody CustomerLocation customerLocation ){
		CustomerLocation aux = repository.save(customerLocation);
		return aux.getId()!=null? new ResponseEntity<>(aux, HttpStatus.CREATED): new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/customer-location/near/{lon}/{lat}")
	public ResponseEntity<CustomerLocation> findNear(@PathVariable String lon,@PathVariable String lat){
		double longitude, latitude;
		longitude = Double.parseDouble(lon);
		latitude = Double.parseDouble(lat);
		Point point = new Point(longitude, latitude);
		Page<CustomerLocation> page = repository.findByPointNear(point, PageRequest.of(0, 1));
		return page.getTotalElements() > 1 ? new ResponseEntity<>(page.toList().get(0), HttpStatus.OK):new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
