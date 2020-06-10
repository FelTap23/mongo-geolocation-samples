package com.tapiax.samples.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tapiax.samples.model.AreaTown;
import com.tapiax.samples.repository.AreaRepository;

@RestController
public class AreaTownController {
	
	
	@Autowired
	private AreaRepository repository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@PostMapping("/area-town")
	public ResponseEntity<AreaTown> createAreaTown(@RequestBody AreaTown areaTown){
		AreaTown aux = repository.save(areaTown);
		return aux.getId() != null ? new ResponseEntity<>(aux,HttpStatus.CREATED) : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
	}
	
	@GetMapping("/area-town")
	public List<AreaTown> findAreaTown(){
		return repository.findAll();
	}
	
	@GetMapping("/area-town/{lon}/{lat}")
	public List<AreaTown> pointWithinArea(@PathVariable String lon, @PathVariable String lat){
		double longitude, latitude;
		longitude = Double.parseDouble(lon);
		latitude = Double.parseDouble(lat);
		
		Point point = new Point(longitude, latitude);
		
		Query query = new Query().addCriteria(Criteria.where("area").intersects(new GeoJsonPoint(point)));
		List<AreaTown> areas = mongoTemplate.find(query, AreaTown.class);
		return areas;
	}
	
}
