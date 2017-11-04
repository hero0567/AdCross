package com.crossp.jpa.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.AppEventData;


@Service
public interface AppEventRepository extends CrudRepository<AppEventData, Long> {

}
