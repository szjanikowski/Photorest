package com.hipermind.photorest.service;

import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Service;

import com.hipermind.photorest.dao.PhotoDao;
import com.hipermind.photorest.domain.Photo;
import com.hipermind.photorest.domain.PhotoData;


@Service
@Scope("singleton")
public class PhotoService {
	
	private static final Logger  logger = Logger.getLogger(PhotoService.class.getName());

	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private PhotoDataService photoDataService;
	
	
	
	public Photo createNewPhoto(InputStream uploadedInputStream,
			String fileName) {
		
		PhotoData photoData = photoDataService.createNewPhotoData(uploadedInputStream, fileName);
		
		Photo newPhoto = new Photo(fileName, "", -1, -1, photoData.getId(), null, null);
		
		photoDao.insert(newPhoto);
		
		logger.log(Level.INFO, "A photo resource has been created: " + newPhoto);
		
		return newPhoto;
		
	}
	
	public Photo getPhotoById(String id) {
		ObjectId objId = MongoService.convertObjectId(id);
		return photoDao.findOne(Query.query(Criteria.where("_id").is(objId)));
	}

	public Object getPhotoByName(String name) {
		return photoDao.findOne(Query.query(Criteria.where("name").is(name)));
	}

	public List<Photo> getAllPhotos() {
		return photoDao.find(new Query());
	}
	
	public void deleteAllPhotos() {
		photoDao.delete(new Query());
	}

	public void deletePhotoById(String id) {
		ObjectId objId = MongoService.convertObjectId(id);
		photoDao.delete(Query.query(Criteria.where("_id").is(objId)));
		
	}
	
	

}
