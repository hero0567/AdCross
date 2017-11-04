package com.crossp.jpa.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.AppSpace;
import com.crossp.jpa.domain.User;


@Service
public interface AppSpaceRepository extends CrudRepository<AppSpace, Long> {
	
	public List<AppSpace> findByUser(User user);
	
}
