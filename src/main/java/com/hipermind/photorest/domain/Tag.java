package com.hipermind.photorest.domain;

public class Tag {
	
	String id;
	Person person;
	Double xPos;
	Double yPos;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Double getxPos() {
		return xPos;
	}
	public void setxPos(Double xPos) {
		this.xPos = xPos;
	}
	public Double getyPos() {
		return yPos;
	}
	public void setyPos(Double yPos) {
		this.yPos = yPos;
	}
	
	

}
