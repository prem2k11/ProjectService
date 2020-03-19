package com.fse.microservice.project.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.microservice.project.service.entity.ParentTaskEntity;



public interface ParentTaskRepository extends JpaRepository<ParentTaskEntity, Integer>{

	
}
