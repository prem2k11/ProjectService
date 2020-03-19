package com.fse.microservice.project.service.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.server.PathParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.microservice.project.service.entity.ProjectServiceEntity;
import com.fse.microservice.project.service.entity.TaskEntity;
import com.fse.microservice.project.service.entity.UserEntity;
import com.fse.microservice.project.service.repository.ProjectServiceRepository;
import com.fse.microservice.project.service.repository.UserServiceRepository;


@RestController
/*
 * @RequestMapping(path="/project")
 */
@RequestMapping(value = "/project")
public class ProjectServiceController {

	@Autowired
	private ProjectServiceRepository projectServiceRepository;
	
	@Autowired
	private UserServiceRepository userServiceRepository;
	
	@RequestMapping(value = "/addproject", method = RequestMethod.POST)
	public ResponseEntity<String> addProject(@RequestBody ProjectServiceEntity entity)
	{
		projectServiceRepository.save(entity);
		UserEntity userEntity = entity.getUser();
		userServiceRepository.save(userEntity);
		return new ResponseEntity<String>("Project Added Successfully",HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getproject", method = RequestMethod.GET)
	public ResponseEntity<ProjectServiceEntity> getProject(@RequestParam("id") int id)
	{
		ProjectServiceEntity projectServiceEntity = new ProjectServiceEntity();
			
			projectServiceEntity = (ProjectServiceEntity) projectServiceRepository.findByprojectid(id);
			return new ResponseEntity<ProjectServiceEntity>(projectServiceEntity,HttpStatus.OK);

	}
	
	@RequestMapping(value = "/getallproject", method = RequestMethod.GET)
	public ResponseEntity<List<ProjectServiceEntity>> getAllProject()
	{
		List<ProjectServiceEntity> projectServiceEntity = new ArrayList<ProjectServiceEntity>();
		projectServiceEntity = (List<ProjectServiceEntity>) projectServiceRepository.findAll();
		if(projectServiceEntity != null)
		{
			List<ProjectServiceEntity> prjList = new ArrayList<ProjectServiceEntity>();
			for (ProjectServiceEntity prj : projectServiceEntity) {
				prj.setNoOfTask(getTaskListByProject(prj.getProjectid()).size());
				prj.setNofCompTask(getCompletedTask(prj.getProjectid()));
				prjList.add(prj);
			}
			return new ResponseEntity<List<ProjectServiceEntity>>(prjList,HttpStatus.OK);
			
		}
		
		return new ResponseEntity<List<ProjectServiceEntity>>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/updateproject", method = RequestMethod.POST)
	public ResponseEntity<String> updateProject(@RequestBody ProjectServiceEntity entity)
	{
		 
		if(projectServiceRepository.findByprojectid(entity.getProjectid()) != null)
		{
			projectServiceRepository.save(entity);
			return new ResponseEntity<String>("Project Updated Successfully",HttpStatus.OK);
		}
		return new ResponseEntity<String>("No Project Found",HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/getallsortedproject", method = RequestMethod.GET)
	public ResponseEntity<List<ProjectServiceEntity>> getAllSortedUser(@RequestParam("sortname") String sortname)
	{
		Sort sortOrder = Sort.by(sortname);
		List<ProjectServiceEntity> projEntity = new ArrayList<ProjectServiceEntity>();
		projEntity = (List<ProjectServiceEntity>) projectServiceRepository.findAll(sortOrder);
		if(projEntity != null)
		{
			return new ResponseEntity<List<ProjectServiceEntity>>(projEntity,HttpStatus.OK);
		}
		return new ResponseEntity<List<ProjectServiceEntity>>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/deleteproject", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteProject(@RequestParam("id") String id)
	{
	
		 if(!StringUtils.isEmpty(id)) { 
			 int projectId = Integer.parseInt(id);
			 projectServiceRepository.deleteById(projectId); 
			 return new ResponseEntity<String>("Project Detail Deleted",HttpStatus.OK); 
		}
		 
		
		return new ResponseEntity<String>("Project Detail Not Found",HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/searchproject", method = RequestMethod.GET)
	public ResponseEntity<List<ProjectServiceEntity> > searchUser(
			@RequestParam("value") String value)
	{
		List<ProjectServiceEntity> projEntity = new ArrayList<ProjectServiceEntity>();
		if(value != null && !value.isEmpty())
		{
			if(!StringUtils.isEmpty(value))
			{
		
				projEntity = (List<ProjectServiceEntity> ) projectServiceRepository.findByprojectnm(value);
			}
			
			return new ResponseEntity<List<ProjectServiceEntity> >(projEntity,HttpStatus.OK);
			
		}
		return new ResponseEntity<List<ProjectServiceEntity>>(HttpStatus.BAD_REQUEST);
	}
	
	public Set<TaskEntity> getTaskListByProject(int projectId) { 
		  ProjectServiceEntity project = projectServiceRepository.findByprojectid(projectId); 
		  Set<TaskEntity> taskList = new HashSet<TaskEntity>(); 
		  taskList = project.getTaskList();
		  project.setNoOfTask(taskList.size());
		  return taskList;
	}
	 
	  
	  public int getCompletedTask(int projectId) {
		  Set<TaskEntity> taskList = new HashSet<TaskEntity>(); 
		  taskList = getTaskListByProject(projectId);
		  int completedTask = 0;
		  for (TaskEntity task : taskList) {
			 if( task.getStatus() != null)
				 completedTask++;
		  }
		  System.out.println("completed Task - " +completedTask);
		  return completedTask;
	  }
	 
}
