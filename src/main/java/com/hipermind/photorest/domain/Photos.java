package com.hipermind.photorest.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "photos")
public class Photos {
	@XmlElement(name = "photo")
	private List<Photo> photos;
	
	public Photos() {
		super();
	}

	public Photos(List<Photo> list) {
		super();
		this.photos = list;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}
	
	

}
