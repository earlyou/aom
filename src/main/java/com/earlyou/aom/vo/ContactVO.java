package com.earlyou.aom.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContactVO {
	// Fields
	private int ctsn;		// contact serial number	
	private String ctid; 	// contact id
	private String ctpw; 	// contact password
	private String ctnm; 	// contact name
	private String ctem; 	// contact email
	private int ctcc;		// contact country code
	private String ctpn;	// contact phone number
	private String ctmg; 	// contact message
	private String ctrg; 	// contact register date
	private String ctdd; 	// contact delete date

	// Constructors
	/**
	 * for Insert
	 */
	public ContactVO(String ctid, String ctpw, String ctnm, String ctem, int ctcc, String ctpn, String ctmg) {
		super();
		this.ctid = ctid;
		this.ctpw = ctpw;
		this.ctnm = ctnm;
		this.ctem = ctem;
		this.ctcc = ctcc;
		this.ctpn = ctpn;
		this.ctmg = ctmg;
	}
	
	/**
	 * for Update
	 */
	public ContactVO(int ctsn, String ctid, String ctpw, String ctnm, String ctem, int ctcc, String ctpn, String ctmg) {
		super();
		this.ctsn = ctsn;
		this.ctid = ctid;
		this.ctpw = ctpw;
		this.ctnm = ctnm;
		this.ctem = ctem;
		this.ctcc = ctcc;
		this.ctpn = ctpn;
	}
	

	/**
	 * for Delete, Select
	 */
	public ContactVO(int ctsn) {
		this.ctsn = ctsn;
	}

}
