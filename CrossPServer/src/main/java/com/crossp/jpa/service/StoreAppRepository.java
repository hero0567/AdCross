package com.crossp.jpa.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.StoreApp;


@Service
public interface StoreAppRepository extends CrudRepository<StoreApp, Long> {

}
