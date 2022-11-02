package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    // DONE: Map columns in data table CURVEPOINT with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Digits(integer = 10, fraction = 0)
	@Min(value = 0, message = "Must be positive")
	@NotNull(message = "Numbers required")
	@Column(name = "curveId")
	private Integer curveId;
	
	@Column(name = "asOfDate")
	private LocalDateTime asOfDate;
	
	@Digits(integer = 10, fraction = 2)
	@Min(value = 0, message = "Must be positive")
	@NotNull(message = "Numbers required")
	@Column(name = "term")
	private Double term;
	
	@Digits(integer = 10, fraction = 2)
	@Min(value = 0, message = "Must be positive")
	@NotNull(message = "Numbers required")
	@Column(name = "value")
	private Double value;
	
	@Column(name = "creationDate")
	private LocalDateTime creationDate;

	
	// ======= Constructors ===================================================
	public CurvePoint() {
		super();
	}
	
	public CurvePoint(Integer curveId, Double term, Double value) {
		super();
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}

	
	// ======= Getters and setters ============================================
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurveId() {
		return curveId;
	}

	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}

	public LocalDateTime getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(LocalDateTime asOfDate) {
		this.asOfDate = asOfDate;
	}

	public Double getTerm() {
		return term;
	}

	public void setTerm(Double term) {
		this.term = term;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}
	
}
