package com.fse.microservice.project.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.microservice.project.service.entity.ProjectServiceEntity;
import com.fse.microservice.project.service.entity.UserEntity;



public interface ProjectServiceRepository extends JpaRepository<ProjectServiceEntity, Integer>{

	ProjectServiceEntity findByprojectid(int projectid);
	
	List<ProjectServiceEntity> findByprojectnm(String projectnm);
	
	void deleteByuser( UserEntity entity);

}
