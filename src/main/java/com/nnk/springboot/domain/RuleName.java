package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "rulename")
public class RuleName {
    // DONE: Map columns in data table RULENAME with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@NotBlank(message = "Name is mandatory")
	@Column(name = "name", length = 125)
	private String name;
	
	@NotBlank(message = "Description is mandatory")
	@Column(name = "description", length = 125)
	private String description;
	
	@NotBlank(message = "Json is mandatory")
	@Column(name = "json", length = 125)
	private String json;
	
	@NotBlank(message = "Template is mandatory")
	@Column(name = "template", length = 512)
	private String template;
	
	@NotBlank(message = "SqlStr is mandatory")
	@Column(name = "sqlStr", length = 125)
	private String sqlStr;
	
	@NotBlank(message = "SqlPart is mandatory")
	@Column(name = "sqlPart", length = 125)
	private String sqlPart;

	
	// ======= Constructors ===================================================
	public RuleName() {
		super();
	}
	
	public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
		super();
		this.name = name;
		this.description = description;
		this.json = json;
		this.template = template;
		this.sqlStr = sqlStr;
		this.sqlPart = sqlPart;
	}

	
	// ======= Getters and setters ============================================
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getSqlStr() {
		return sqlStr;
	}

	public void setSqlStr(String sqlStr) {
		this.sqlStr = sqlStr;
	}

	public String getSqlPart() {
		return sqlPart;
	}

	public void setSqlPart(String sqlPart) {
		this.sqlPart = sqlPart;
	}
	
}
