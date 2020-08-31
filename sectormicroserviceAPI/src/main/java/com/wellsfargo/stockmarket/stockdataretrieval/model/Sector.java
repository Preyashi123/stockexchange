package com.wellsfargo.stockmarket.stockdataretrieval.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;


@Entity
@Table(name="sector")
public class Sector {
	@Id
	private Long sectorId;
	@Column
	private String sectorName;
	@Column
	private String sectorBrief;
	public Sector() {
		
	}
	public Sector(Long sectorId, String sectorName, String sectorBrief) {
		super();
		this.sectorId = sectorId;
		this.sectorName = sectorName;
		this.sectorBrief = sectorBrief;
	}
	public long getSectorId() {
		return sectorId;
	}
	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getSectorBrief() {
		return sectorBrief;
	}
	public void setSectorBrief(String sectorBrief) {
		this.sectorBrief = sectorBrief;
	}
	@Override
	public String toString() {
		return "Sector [sectorId=" + sectorId + ", sectorName=" + sectorName + ", sectorBrief=" + sectorBrief + "]";
	}
}
