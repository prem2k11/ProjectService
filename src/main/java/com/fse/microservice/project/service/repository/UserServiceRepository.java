package com.fse.microservice.project.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fse.microservice.project.service.entity.UserEntity;



public interface UserServiceRepository extends JpaRepository<UserEntity, Integer>{

	UserEntity findByuserid(int userid);
	
	List<UserEntity> findByfirstname(String firstname);
	
	List<UserEntity> findBylastname(String lastname);
	
	List<UserEntity> findByemployeeid(String employeeid);
	
}
