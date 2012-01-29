package com.hipermind.photorest.domain;

import org.springframework.data.annotation.Id;

/**
 * Represents data about person. Can be stored in MongoDB and at the same time serialized to xml.
 * @author sjanikowski
 *
 */
public class Person {
	@Id
	String id;
	String name;
	String surname;
	
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
