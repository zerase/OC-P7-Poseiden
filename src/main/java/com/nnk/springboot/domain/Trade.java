package com.nnk.springboot.domain;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.LocalDateTime;


@Entity
@Table(name = "trade")
public class Trade {
    // DONE: Map columns in data table TRADE with corresponding java fields
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tradeId")
	private Integer tradeId;
	
	@Pattern(regexp = "^[A-Z a-z]*$", message = "Account has to be text")
	@NotBlank(message = "Account is mandatory")
	@Column(name = "account", length = 30)
	private String account;
	
	@Pattern(regexp = "^[A-Z a-z]*$", message = "Type has to be text")
	@NotBlank(message = "Type is mandatory")
	@Column(name = "type", length = 30)
	private String type;
	
	@Digits(integer = 10, fraction = 2)
	@Min(value = 0, message = "Must be positive")
	//@NotNull(message = "Numbers required")
	@Column(name = "buyQuantity")
	private Double buyQuantity;
	
	@Column(name = "sellQuantity")
	private Double sellQuantity;
	
	@Column(name = "buyPrice")
	private Double buyPrice;
	
	@Column(name = "sellPrice")
	private Double sellPrice;
	
	@Column(name = "benchmark", length = 125)
	private String benchmark;
	
	@Column(name = "tradeDate")
	private LocalDateTime tradeDate;
	
	@Column(name = "security", length = 125)
	private String security;
	
	@Column(name = "status", length = 10)
	private String status;
	
	@Column(name = "trdaer", length = 125)
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
	public Trade() {
		super();
	}
	
	public Trade(String account, String type) {
		super();
		this.account = account;
		this.type = type;
	}
	
	public Trade(String account, String type, Double buyQuantity) {
		super();
		this.account = account;
		this.type = type;
		this.buyQuantity = buyQuantity;
	}

	
	// ======= Getters and setters ============================================
	public Integer getTradeId() {
		return tradeId;
	}

	public void setTradeId(Integer tradeId) {
		this.tradeId = tradeId;
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

	public Double getBuyQuantity() {
		return buyQuantity;
	}

	public void setBuyQuantity(Double buyQuantity) {
		this.buyQuantity = buyQuantity;
	}

	public Double getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(Double sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(Double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getBenchmark() {
		return benchmark;
	}

	public void setBenchmark(String benchmark) {
		this.benchmark = benchmark;
	}

	public LocalDateTime getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(LocalDateTime tradeDate) {
		this.tradeDate = tradeDate;
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
