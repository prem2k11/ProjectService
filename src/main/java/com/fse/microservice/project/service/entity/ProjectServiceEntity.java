package com.fse.microservice.project.service.entity;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="projects")
public class ProjectServiceEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectid",  nullable=false, length=10)
	private int projectid;
	
	@Column(name="projectnm")
	private String projectnm;
	
	@Column(name="startdate")
	private LocalDate  startdate;
	
	@Column(name="enddate")
	private LocalDate  enddate;
	
	@Column(name="priority")
	private int priority;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "userid")
	private UserEntity user;
	
	@Transient
	private String status;
	
	@Transient
	private int noOfTask;
	
	@Transient
	private int nofCompTask;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "projectServiceEntity", fetch = FetchType.EAGER)
	private Set<TaskEntity> taskList = new HashSet<TaskEntity>();

	public Set<TaskEntity> getTaskList() {
		return taskList;
	}

	public void setTaskList(Set<TaskEntity> taskList) {
		this.taskList = taskList;
	}

	public int getProjectid() {
		return projectid;
	}

	public void setProjectid(int projectid) {
		this.projectid = projectid;
	}

	public String getProjectnm() {
		return projectnm;
	}

	public void setProjectnm(String projectnm) {
		this.projectnm = projectnm;
	}

	public LocalDate  getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate  startdate) {
		this.startdate = startdate;
	}

	public LocalDate  getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate  enddate) {
		this.enddate = enddate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getNoOfTask() {
		return noOfTask;
	}

	public void setNoOfTask(int noOfTask) {
		this.noOfTask = noOfTask;
	}

	public int getNofCompTask() {
		return nofCompTask;
	}

	public void setNofCompTask(int nofCompTask) {
		this.nofCompTask = nofCompTask;
	}
	
}
