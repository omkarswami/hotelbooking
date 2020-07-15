package com.omkarswami.hotelbooking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
	private Integer availablePoints;

	public User() {

	}

	public User(Integer id, String name, Integer points) {
		super();
		this.id = id;
		this.name = name;
		this.availablePoints = points;
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(Integer availablePoints) {
		this.availablePoints = availablePoints;
	}



}
