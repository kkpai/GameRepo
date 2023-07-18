package com.example.demo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//Game is a simple object with name(unique), date of creation, active.

@Entity
public class Game {

	@Id
	int id;
	String name;
	String date; // can also use java.sql.Date instead
	boolean active;

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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
