package com.crossp.jpa.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.AppItemArea;


@Service
public interface AppItemAreaRepository extends CrudRepository<AppItemArea, Long> {

}
