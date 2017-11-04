package com.crossp.jpa.service;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.AppMessage;


@Service
public interface AppMessageRepository extends CrudRepository<AppMessage, Long> {

	public List<AppMessage> findByCid(Long id);
	
	public List<AppMessage> findByPid(Long id);
	
	public List<AppMessage> findByCidAndStatus(Long id, int status);
	
	public AppMessage findByIdAndCid(Long id, Long cid);
}
