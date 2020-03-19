package com.fse.microservice.project.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="parent_task")
public class ParentTaskEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "parentid", nullable = false, length=10)
	private int parentid;
	
	@Column(name="parenttask")
	private String parenttask;
	
	public int getParentid() {
		return parentid;
	}
	public void setParentid(int parentid) {
		this.parentid = parentid;
	}
	public String getParenttask() {
		return parenttask;
	}
	public void setParenttask(String parenttask) {
		this.parenttask = parenttask;
	}

	
	

}
