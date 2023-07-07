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
public class CpuVO {
	// Fields
	private String cname;			/** cpu name */
	private String carch;			/** cpu MicroArchitecture */
	private int cpc;				/** cpu physical package count */
	private int cppc;				/** cpu physical processor count */
	private int clpc;				/** cpu logical processor count */
		
	// Constructors
	
}
