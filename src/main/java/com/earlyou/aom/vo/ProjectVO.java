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
public class ProjectVO {
	// Fields
	private String pjnm;		/** project name */
	private String amid;		/** admin ID */
	private String pjdm;		/** project domain */
	private String pjdd;		/** project dev date */
	private String pjrd;		/** project register date */
	private String pjdp;		/** project deprecated date */
	
	// Constructors
	/**
	 * for Update, Insert
	 */
	public ProjectVO(String pjnm, String amid, String pjdm, String pjdd) {
		super();
		this.pjnm = pjnm;
		this.amid = amid;
		this.pjdm = pjdm;
		this.pjdd = pjdd;
	}

	/**
	 * for Delete, Select
	 */
	public ProjectVO(String pjnm) {
		this.pjnm = pjnm;
	}
}
