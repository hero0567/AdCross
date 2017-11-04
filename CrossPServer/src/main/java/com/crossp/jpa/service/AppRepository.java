package com.crossp.jpa.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.App;
import com.crossp.jpa.domain.User;


@Service
public interface AppRepository extends CrudRepository<App, Long> {

	public List<App> findByUser(User user);
	
	public List<App> findByUserId(Long id);
	
	public App findByToken(String token);
	
	@Query(value = "select * from app c where c.id in "
			+ "(select b.apps_id from application a, app_app b where a.id=b.app_id and a.id = ?)", nativeQuery=true)
	public List<App> findByApplicationId(int id);
	
	@Query(value = "select * from app c where c.id not in "
			+ "(select b.apps_id from app a, app_app b where a.id=b.app_id and a.id = ?);", nativeQuery=true)
	public List<App> findUnJoinByApplicationId(int id);

}
