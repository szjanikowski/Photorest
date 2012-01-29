package com.hipermind.photorest.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.document.mongodb.index.Indexed;
import org.springframework.data.document.mongodb.mapping.DBRef;

/**
 * Represents metadata of photo. Can be stored in MongoDB and at the same time serialized to xml.
 * @author sjanikowski
 *
 */
@XmlRootElement(name = "Photo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Photo {
	
	@Id
	private String id;
	
	@Indexed
	private String name;
	
	private String description;
	
	private int width;
	private int height;
	
	private String photoDataId;
	
	@DBRef
	private Person portraitedPerson;
	
	@DBRef
	private List<Tag> tags;
	
	public Photo() {}

	public Photo(String name, String description, int width,
			int height, String photoDataId, Person portraitedPerson,
			List<Tag> tags) {
		super();
		this.name = name;
		this.description = description;
		this.width = width;
		this.height = height;
		this.photoDataId = photoDataId;
		this.portraitedPerson = portraitedPerson;
		this.tags = tags;
	}



	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPhotoDataId() {
		return photoDataId;
	}

	public Person getPortraitedPerson() {
		return portraitedPerson;
	}

	public void setPortraitedPerson(Person portraitedPerson) {
		this.portraitedPerson = portraitedPerson;
	}
	
	public boolean hasProtraitedPerson() {
		return portraitedPerson == null ? false : true;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void addTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "Photo [id=" + id + ", name=" + name + ", description="
				+ description + ", width=" + width + ", height=" + height
				+ ", photoDataId=" + photoDataId + "]";
	}
	
}
