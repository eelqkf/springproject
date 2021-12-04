package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "manager")
public class Manager {

	@Id
	@Column(name = "manager_id")
	private String managerId;
	@Column(nullable = false, length = 100)
	private String managerPassword;

	public Manager(String managerId, String managerPassword) {
		this.managerId = managerId;
		this.managerPassword = managerPassword;
	}

	public Manager() {

	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerPassword() {
		return managerPassword;
	}

	public void setManagerPassword(String managerPassword) {
		this.managerPassword = managerPassword;
	}

}
