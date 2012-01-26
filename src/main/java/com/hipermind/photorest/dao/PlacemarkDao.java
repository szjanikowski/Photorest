package com.hipermind.photorest.dao;


import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.hipermind.photorest.kml.Placemark;

/**
 * Data Access Object (DAO) for a {@link Placemark}.
 * 
 * @author Patrick Ruhkopf
 */
@Repository
@Scope("singleton")
public class PlacemarkDao extends AbstractMongoDao<Placemark>
{
	

}
