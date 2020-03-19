package com.fse.microservice.project.service.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tasks")
public class TaskEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskid")
	private int taskid;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "parentid")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ParentTaskEntity parentTaskEntity;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "projectid")
	@JsonBackReference
	private ProjectServiceEntity projectServiceEntity;
	
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "userid")
	private UserEntity userEntity;

	@Column(name="taskname")
	private String taskname;
	
	@Column(name="startdate")
	private LocalDate  startdate;
	
	@Column(name="enddate")
	private LocalDate  enddate;
	
	@Column(name="priority")
	private int priority;
	
	@Column(name="status")
	private String status;
	

	public ParentTaskEntity getParentTaskEntity() {
		return parentTaskEntity;
	}

	public void setParentTaskEntity(ParentTaskEntity parentTaskEntity) {
		this.parentTaskEntity = parentTaskEntity;
	}

	public ProjectServiceEntity getProjectServiceEntity() {
		return projectServiceEntity;
	}

	public void setProjectServiceEntity(ProjectServiceEntity projectServiceEntity) {
		this.projectServiceEntity = projectServiceEntity;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public int getTaskid() {
		return taskid;
	}

	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	
}
