package com.hipermind.photorest.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.hipermind.photorest.domain.Photo;

@Repository
@Scope("singleton")
public class PhotoDao extends AbstractMongoDao<Photo> {

}
