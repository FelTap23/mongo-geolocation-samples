package com.tapiax.samples.model;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.geo.GeoJsonPolygon;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AreaTown {
	@Id
	private String id;
	private String name;
	
	@GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
	private GeoJsonPolygon  area;

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

	public GeoJsonPolygon getArea() {
		return area;
	}

	public void setArea(GeoJsonPolygon area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "AreaTown [id=" + id + ", name=" + name + ", area=" + area + "]";
	}

}
