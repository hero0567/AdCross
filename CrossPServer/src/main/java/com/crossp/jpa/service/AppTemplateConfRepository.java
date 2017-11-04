package com.crossp.jpa.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.crossp.jpa.domain.AppTemplate;
import com.crossp.jpa.domain.AppTemplateConf;


@Service
public interface AppTemplateConfRepository extends CrudRepository<AppTemplateConf, Long> {

}
