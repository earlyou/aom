package com.earlyou.aom.vo;

import java.util.Date;

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
public class AdminVO {
	// Fields
	private String amid;
	private Boolean ammp;
	private String ampw;
	private String amnm;
	private String amgd;
	private int amby;
	private int ambm;
	private int ambd;
	private String amem;
	private int amcc;
	private String ampn;
	private String amlc;
	private String amab;
	private Date amrd;
	private Date amdd;
	
	// Constructors
	/** 
	 * no amrd,amab -> for Update, Insert
	 */
	public AdminVO(String amid, Boolean ammp, String ampw, String amnm, String amgd, int amby, int ambm, int ambd,
			String amem, int amcc, String ampn, String amlc, String amab) {
		super();
		this.amid = amid;
		this.ammp = ammp;
		this.ampw = ampw;
		this.amnm = amnm;
		this.amgd = amgd;
		this.amby = amby;
		this.ambm = ambm;
		this.ambd = ambd;
		this.amem = amem;
		this.amcc = amcc;
		this.ampn = ampn;
		this.amlc = amlc;
		this.amab = amab;
	}

	/**
	 * for Delete, Select
	 */
	public AdminVO(String amid) {
		super();
		this.amid = amid;
	}
	
}
