package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


@Entity
@Table(name = "rating")
public class Rating {
    // DONE: Map columns in data table RATING with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	
	@Pattern(regexp = "^[A-Z a-z]*$", message = "MoodysRating has to be text")
	@NotBlank(message = "MoodysRating is mandatory")
	@Column(name = "moodysRating", length = 125)
	private String moodysRating;
	
	@Pattern(regexp = "^[A-Z a-z]*$", message = "SandPRating has to be text")
	@NotBlank(message = "SandPRating is mandatory")
	@Column(name = "sandPRating", length = 125)
	private String sandPRating;
	
	@Pattern(regexp = "^[A-Z a-z]*$", message = "Fitch has to be text")
	@NotBlank(message = "Fitch is mandatory")
	@Column(name = "fitchRating", length = 125)
	private String fitchRating;
	
	@Digits(integer = 10, fraction = 0)
	@Min(value = 0, message = "Must be positive")
	@NotNull(message = "Numbers required")
	@Column(name = "orderNumber")
	private Integer orderNumber;

	
	// ======= Constructors ===================================================
	public Rating() {
		super();
	}
	
	public Rating(String moodysRating, String sandPRating, String fitchRating, Integer orderNumber) {
		super();
		this.moodysRating = moodysRating;
		this.sandPRating = sandPRating;
		this.fitchRating = fitchRating;
		this.orderNumber = orderNumber;
	}

	
	// ======= Getters and setters ============================================
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMoodysRating() {
		return moodysRating;
	}

	public void setMoodysRating(String moodysRating) {
		this.moodysRating = moodysRating;
	}

	public String getSandPRating() {
		return sandPRating;
	}

	public void setSandPRating(String sandPRating) {
		this.sandPRating = sandPRating;
	}

	public String getFitchRating() {
		return fitchRating;
	}

	public void setFitchRating(String fitchRating) {
		this.fitchRating = fitchRating;
	}

	public Integer getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Integer orderNumber) {
		this.orderNumber = orderNumber;
	}
	
}
