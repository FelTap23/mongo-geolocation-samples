package com.tapiax.samples.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.geo.Point;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.tapiax.samples.model.CustomerLocation;

public interface CustomerLocationRepository extends PagingAndSortingRepository<CustomerLocation,String> {
	Page<CustomerLocation> findByPointNear(Point point, Pageable pageable);
}
