package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.LocalDateTime;

@Entity
@Table(name = "bidlist")
public class BidList {
    // DONE: Map columns in data table BIDLIST with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bidListId")
	private Integer bidListId;
	
	@Pattern(regexp = "^[A-Za-z]*$", message = "Account has to be text")
	@NotBlank(message = "Account is mandatory")
	@Column(name = "account", length = 30)
	private String account;
	
	@Pattern(regexp = "^[A-Za-z]*$", message = "Type has to be text")
	@NotBlank(message = "Type is mandatory")
	@Column(name = "type", length = 30)
	private String type;
	
	@Digits(integer = 10, fraction = 2)
	@Min(value = 0, message = "Must be positive")
	@NotNull(message = "Numbers required")
	@Column(name = "bidQuantity")
	private Double bidQuantity;
	
	@Column(name = "askQuantity")
	private Double askQuantity;
	
	@Column(name = "bid")
	private Double bid;
	
	@Column(name = "ask")
	private Double ask;
	
	@Column(name = "benchmark", length = 125)
	private String benchmark;
	
	@Column(name = "bidListDate")
	private LocalDateTime bidListDate;
	
	@Column(name = "commentary", length = 125)
	private String commentary;
	
	@Column(name = "security", length = 125)
	private String security;
	
	@Column(name = "status", length = 10)
	private String status;
	
	@Column(name = "trader", length = 125)
	private String trader;
	
	@Column(name = "book", length = 125)
	private String book;
	
	@Column(name = "creationName", length = 125)
	private String creationName;
	
	@Column(name = "creationDate")
	private LocalDateTime creationDate;
	
	@Column(name = "revisionName", length = 125)
	private String revisionName;
	
	@Column(name = "revisionDate")
	private LocalDateTime revisionDate;
	
	@Column(name = "dealName", length = 125)
	private String dealName;
	
	@Column(name = "dealType", length = 125)
	private String dealType;
	
	@Column(name = "sourceListId", length = 125)
	private String sourceListId;
	
	@Column(name = "side", length = 125)
	private String side;
	
	
	// ======= Constructors ===================================================
	public BidList() {
		super();
	}
	
	public BidList(String account, String type, Double bidQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.bidQuantity = bidQuantity;
	}

	
	// ======= Getters and setters ===========================================
	public Integer getBidListId() {
		return bidListId;
	}

	public void setBidListId(Integer bidListId) {
		this.bidListId = bidListId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Double getBidQuantity() {
		return bidQuantity;
	}

	public void setBidQuantity(Double bidQuantity) {
		this.bidQuantity = bidQuantity;
	}

	public Double getAskQuantity() {
		return askQuantity;
	}

	public void setAskQuantity(Double askQuantity) {
		this.askQuantity = askQuantity;
	}

	public Double getBid() {
		return bid;
	}

	public void setBid(Double bid) {
		this.bid = bid;
	}

	public Double getAsk() {
		return ask;
	}

	public void setAsk(Double ask) {
		this.ask = ask;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public LocalDateTime getBidListDate() {
		return bidListDate;
	}

	public void setBidListDate(LocalDateTime bidListDate) {
		this.bidListDate = bidListDate;
	}

	public String getCommentary() {
		return commentary;
	}

	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTrader() {
		return trader;
	}

	public void setTrader(String trader) {
		this.trader = trader;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String book) {
		this.book = book;
	}

	public String getCreationName() {
		return creationName;
	}

	public void setCreationName(String creationName) {
		this.creationName = creationName;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getRevisionName() {
		return revisionName;
	}

	public void setRevisionName(String revisionName) {
		this.revisionName = revisionName;
	}

	public LocalDateTime getRevisionDate() {
		return revisionDate;
	}

	public void setRevisionDate(LocalDateTime revisionDate) {
		this.revisionDate = revisionDate;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getSourceListId() {
		return sourceListId;
	}

	public void setSourceListId(String sourceListId) {
		this.sourceListId = sourceListId;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}
	
}
