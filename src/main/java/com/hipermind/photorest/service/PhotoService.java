package com.hipermind.photorest.service;

import java.io.InputStream;
import java.util.Iterator;
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


/**
 * Service for handling the operations related to Photo object
 * @author sjanikowski
 *
 */
@Service
@Scope("singleton")
public class PhotoService {
	
	private static final Logger  logger = Logger.getLogger(PhotoService.class.getName());

	@Autowired
	private PhotoDao photoDao;
	
	@Autowired
	private PhotoDataService photoDataService;
	
	
	/**
	 * Creates a new Photo object and corresonding PhotoData object in the DataBase
	 * @param uploadedInputStream
	 * @param fileName
	 * @return
	 */
	public Photo createNewPhoto(InputStream uploadedInputStream,
			String fileName) {
		
		PhotoData photoData = photoDataService.createNewPhotoData(uploadedInputStream, fileName);
		
		Photo newPhoto = new Photo(fileName, "", -1, -1, photoData.getId(), null, null);
		
		photoDao.insert(newPhoto);
		
		logger.log(Level.INFO, "A photo resource has been created: " + newPhoto);
		
		return newPhoto;
		
	}
	/**
	 * Retrieves a photo from the DataBase using id.
	 * @param id
	 * @return
	 */
	public Photo getPhotoById(String id) {
		ObjectId objId = MongoService.convertObjectId(id);
		return photoDao.findOne(Query.query(Criteria.where("_id").is(objId)));
	}

	/**
	 * Retrieves a photo from the DataBase using name.
	 * @param id
	 * @return
	 */
	public Object getPhotoByName(String name) {
		return photoDao.findOne(Query.query(Criteria.where("name").is(name)));
	}

	/**
	 * Get the list of all the photos stored in the DB.
	 * @return
	 */
	public List<Photo> getAllPhotos() {
		return photoDao.find(new Query());
	}
	
	/**
	 * Deletes all the photos objects and corresponding PhotoData objects
	 */
	public void deleteAllPhotos() {
		
		// Delete related photo data
		List<Photo> allPhotos = getAllPhotos();
		Iterator<Photo> allPhotosIt = allPhotos.iterator();
		while(allPhotosIt.hasNext())
		{
			Photo currPhoto = allPhotosIt.next();
			photoDataService.deletePhotoData(currPhoto.getPhotoDataId());
		}
		
		// Delete photo info objects
		photoDao.delete(new Query());
		
	}

	/**
	 * Deletes an object with a given id
	 * @param id
	 */
	public void deletePhotoById(String id) {
		ObjectId objId = MongoService.convertObjectId(id);
		photoDao.delete(Query.query(Criteria.where("_id").is(objId)));
		
	}
	
	
	
	
	

}
