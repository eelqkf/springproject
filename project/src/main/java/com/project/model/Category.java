package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ca_id;

	@Column(unique = true)
	private String c_name;

	public Long getCa_id() {
		return ca_id;
	}

	public void setCa_id(Long ca_id) {
		this.ca_id = ca_id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	@Override
	public String toString() {
		return "Category [id=" + ca_id + ", c_name=" + c_name + "]";
	}

}
