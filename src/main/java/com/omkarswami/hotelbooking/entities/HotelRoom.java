package com.omkarswami.hotelbooking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class HotelRoom {

	@Id
	@GeneratedValue
	private int id;
	private String name;
	private int price;
	
	public HotelRoom() {
		
		
	}
	public HotelRoom(int id, String name, int price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
}
