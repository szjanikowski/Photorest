package com.hipermind.photorest.service;

import java.lang.IllegalStateException;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hipermind.photorest.config.MongoConfig;
import com.mongodb.DB;

/**
 * Handles the operations related to the Mongo DB
 * @author sjanikowski
 *
 */
@Service
@Scope("singleton")
public class MongoService {
	
	private static final Logger  logger = Logger.getLogger(MongoService.class.getName());
	
	@Autowired
	private MongoConfig mongoConfig;
	
	/**
	 * Retrieves the currently used database
	 * @return
	 * @throws IllegalStateException
	 */
	public DB getDb() throws IllegalStateException {
		try {
			return mongoConfig.mongoTemplate().getDb();
		} catch (Exception e) {
			e.printStackTrace();
			throw new IllegalStateException("Mongo database acess exception");
		}
	}
	
	/**
	 * Creates mongo ObjectId from String - returns null if impossible
	 * @param id String with id
	 * @return
	 */
	public static ObjectId convertObjectId(String id) {
		ObjectId objId = null;
		try {
			objId= new ObjectId(id);
		} catch (IllegalArgumentException e) {
			logger.warn("Incorrect id format");
			return null;
		}
		return objId;
	}
	
}
