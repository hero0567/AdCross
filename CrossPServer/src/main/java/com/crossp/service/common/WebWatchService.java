package com.crossp.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.crossp.jpa.domain.StoreApp;
import com.crossp.jpa.service.StoreAppRepository;
import com.crossp.utils.Appstore;

@Component
public class WebWatchService {

	@Autowired
	private StoreAppRepository storeAppRepository;
	
	public void storeApp(String appstoreId){
		StoreApp app = Appstore.getFromWeb(appstoreId);
		storeAppRepository.save(app);
	}
}
