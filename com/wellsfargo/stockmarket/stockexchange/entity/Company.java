package com.wellsfargo.stockmarket.stockexchange.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company", schema = "stockexchange")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "companyid", nullable = false, unique = true)
	private int companyId;

	@Column(name = "companyname", nullable = false, unique = true)
	private String companyName;

	private String ceo;

	private String sector;

	@Column(name = "companycode", nullable = false, unique = true)
	private Integer companyCode;

	@Column(name = "companydetails", nullable = false, unique = true)
	private String companyDetails;

	// Default Constructor
	public Company() {

	}

	// Parameterized Constructor
	public Company(int companyId, String companyName, String ceo, String sector, Integer companyCode,
			String companyDetails) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.ceo = ceo;
		this.sector = sector;
		this.companyCode = companyCode;
		this.companyDetails = companyDetails;
	}

	// Getters and Setters
	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCeo() {
		return ceo;
	}

	public void setCeo(String ceo) {
		this.ceo = ceo;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Integer getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Integer companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyDetails() {
		return companyDetails;
	}

	public void setCompanyDetails(String companyDetails) {
		this.companyDetails = companyDetails;
	}

}
