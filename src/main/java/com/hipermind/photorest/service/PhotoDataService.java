package com.hipermind.photorest.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.apache.log4j.Logger;
import org.bson.BSONObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Service;

import com.hipermind.photorest.domain.PhotoData;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
 

/**
 * Service for handling the operations related to photo graphical data.
 * @author sjanikowski
 *
 */
@Service
@Scope("singleton")
public class PhotoDataService {
 
	private static final Logger  logger = Logger.getLogger(PhotoDataService.class.getName());
	
	@Autowired
	private MongoService mongoService;
 
	/**
	 * Creates a gridFs file in the Mongo Database. Doesn't use the Spring Data Mongo
	 * @param uploadedInputStream
	 * @param fileName
	 * @return
	 */
	// save uploaded file to new location
	public PhotoData createNewPhotoData(InputStream uploadedInputStream,
		String fileName) {
 	
		//DBCollection collection = db.getCollection("testCollection");
		
		// create a "photo" namespace
		GridFS gfsPhoto = new GridFS(mongoService.getDb(),"photo");
		
		// get image file from input stream
		GridFSInputFile gfsFile = gfsPhoto.createFile(uploadedInputStream);
		
		// set the file name identical to the original file name
		gfsFile.setFilename(fileName);
		
		// save the image file into mongoDB
		gfsFile.save();
	
		// print the result
		DBCursor cursor = gfsPhoto.getFileList();
		while (cursor.hasNext()) {
			logger.info(cursor.next());
		}
	
		String id = gfsFile.getId().toString();
		return getPhotoDataById(id);
			
	}
	
	private PhotoData createPhotoData(final GridFSDBFile photo) {
		String id = photo.getId().toString();
		String name = photo.getFilename();

		StreamingOutput streamingOutput = new StreamingOutput() {

			@Override
			public void write(OutputStream outStream) throws IOException,
			WebApplicationException {
				photo.writeTo(outStream);
			}
		};

		return new PhotoData(id,name,streamingOutput);
	}
	
	/**
	 * Retrieves the PhotoData by file name object containing the  graphical Data
	 * @param name
	 * @return
	 */
	public PhotoData getPhotoDataByName(String name) {
		
		// create a "photo" namespace
		GridFS gfsPhoto = new GridFS(mongoService.getDb(),"photo");
		final GridFSDBFile photo = gfsPhoto.findOne(name);
		
		if(photo == null) return null;
		
		return createPhotoData(photo);
		
	}
	
	/**
	 * Retrieves the PhotoData by id object containing the  graphical Data
	 * @param id
	 * @return
	 */
	public PhotoData getPhotoDataById(String id) {
		
		// create a "photo" namespace
		GridFS gfsPhoto = new GridFS(mongoService.getDb(),"photo");
		final GridFSDBFile photo = gfsPhoto.findOne(new ObjectId(id));
		
		if(photo == null) return null;
		
		return createPhotoData(photo);
		
	}
	
	/**
	 * Checks in the database for the PhotoData files with the given name  
	 * @param name
	 * @return
	 */
	public boolean containsName(String name) {
		// create a "photo" namespace
		GridFS gfsPhoto = new GridFS(mongoService.getDb(), "photo");
		GridFSDBFile outputPhoto = gfsPhoto.findOne(name);
		return outputPhoto == null ? false : true;
	}

	/**
	 * Checks in the database for the PhotoData files with the given id
	 * @param id
	 * @return
	 */
	public boolean containsId(String id) {
		GridFS gfsPhoto = new GridFS(mongoService.getDb(), "photo");
		GridFSDBFile outputPhoto = gfsPhoto.findOne(MongoService.convertObjectId(id));
		return outputPhoto == null ? false : true;
	}
	/**
	 * Deletes PhotoData with the given id
	 * @param id
	 */
	public void deletePhotoData(String id) {
		GridFS gfsPhoto = new GridFS(mongoService.getDb(), "photo");
		gfsPhoto.remove(MongoService.convertObjectId(id));

	}
	
	
 
}