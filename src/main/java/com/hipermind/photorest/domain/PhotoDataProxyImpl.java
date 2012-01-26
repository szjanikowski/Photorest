/**
 * 
 */
package com.hipermind.photorest.domain;

import com.hipermind.photorest.service.PhotoDataService;

/**
 * @author sjanikowski
 *
 */
public class PhotoDataProxyImpl implements PhotoDataProxy {

	private String photoDataId;
	
	private PhotoDataService photoDataService;
	
	
	public PhotoDataProxyImpl(String photoDataId, PhotoDataService photoDataService) {
		super();
		this.photoDataId = photoDataId;
	}


	@Override
	public PhotoData getPhotoData() {
		return photoDataService.getPhotoDataById(photoDataId);
	}

}
