package com.tapiax.samples.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CustomerLocation {
	@Id
	private String id;
	private String name;
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private GeoJsonPoint point;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GeoJsonPoint getPoint() {
		return point;
	}
	public void setPoint(GeoJsonPoint point) {
		this.point = point;
	}
	@Override
	public String toString() {
		return "Other [id=" + id + ", name=" + name + ", point=" + point + "]";
	}	
}
