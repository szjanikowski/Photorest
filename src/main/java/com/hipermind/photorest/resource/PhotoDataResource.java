package com.hipermind.photorest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hipermind.photorest.domain.PhotoData;
import com.hipermind.photorest.service.PhotoDataService;

@Path("/photodata")
@Component
@Scope("request")
public class PhotoDataResource {

	final PhotoDataService photoDataService;
	
	@Autowired
	public PhotoDataResource(PhotoDataService photoDataService)
	{
		super();
		this.photoDataService = photoDataService;
		
	}
	
	@GET
	@Path("/name/{photo}")
	@Produces({MediaType.TEXT_HTML, MediaType.MULTIPART_FORM_DATA})
	public Object getPhotoDataByName(
			@PathParam("photo") final String name) {
 
		if (!photoDataService.containsName(name))
			return Response.status(404).build();
		
		PhotoData photoData = photoDataService.getPhotoDataByName(name);
		return photoData.getStreamingOutput();
 
	}
	
	@GET
	@Path("/id/{photo}")
	@Produces({MediaType.TEXT_HTML, MediaType.MULTIPART_FORM_DATA})
	public Object getPhotoDataById(
			@PathParam("photo") final String id) {
 
		if (!photoDataService.containsId(id))
			return Response.status(404).build();
		
		PhotoData photoData = photoDataService.getPhotoDataById(id);
		return photoData.getStreamingOutput();
 
	}
}
