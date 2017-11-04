package com.crossp.jpa.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.AppTemplate;


@Service
public interface AppTemplateRepository extends CrudRepository<AppTemplate, Long> {

}
