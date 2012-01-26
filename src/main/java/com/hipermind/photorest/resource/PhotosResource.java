/**
 * 
 */
package com.hipermind.photorest.resource;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hipermind.photorest.domain.Photo;
import com.hipermind.photorest.domain.Photos;
import com.hipermind.photorest.service.PhotoService;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

/**
 * @author sjanikowski
 *
 */

@Path("/photos")
@Component
@Scope("request")
public class PhotosResource {
	
	private final PhotoService photoService;
	
	@Autowired
	public PhotosResource(PhotoService photoService)
	{
		super();
		this.photoService = photoService;
		
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addPhoto(
		@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {
 
		String fileName = fileDetail.getFileName();
		//String uploadedFileLocation = "c://data/upload/" + fileName;
 
		// save it
		Photo photo = photoService.createNewPhoto(uploadedInputStream, fileName);
 
		String output = "Photo has been stored in MongoDB. Filename: " + fileName + " photo id: " + photo.getId() + " data id: " + photo.getPhotoDataId() ;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getAllPhotos() {
		return Response.ok(new Photos(photoService.getAllPhotos())).build();
	}
	
	@DELETE
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response deleteAllPhotos() {
		photoService.deleteAllPhotos();
		return Response.ok(new Photos(photoService.getAllPhotos())).build();
	}
	
	@GET
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getPhotoById(
			@PathParam("id") final String id) {
		Photo photo = photoService.getPhotoById(id);
		if (photo == null) {
			return Response.noContent().build();
		}
		return Response.ok(photo).build();
		
	}
	
	@DELETE
	@Path("/id/{id}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response deletePhotoById(
			@PathParam("id") final String id) {
		Photo photo = photoService.getPhotoById(id);
		if (photo == null) {
			return Response.noContent().build();
		}
		photoService.deletePhotoById(id);
		return Response.ok().build();
	}
	
	@GET
	@Path("/name/{name}")
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Response getPhotoByName(
			@PathParam("name") final String name) {
		return Response.ok(photoService.getPhotoByName(name)).build();
		
	}
	
	

}
