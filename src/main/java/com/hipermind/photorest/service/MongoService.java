package com.hipermind.photorest.service;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hipermind.photorest.config.MongoConfig;
import com.mongodb.DB;

@Service
@Scope("singleton")
public class MongoService {
	
	private static final Logger  logger = Logger.getLogger(MongoService.class.getName());
	
	@Autowired
	private MongoConfig mongoConfig;
	
	public DB getDb() {
		try {
			return mongoConfig.mongoTemplate().getDb();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
