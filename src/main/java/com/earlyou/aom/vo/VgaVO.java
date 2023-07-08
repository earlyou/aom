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
public class VgaVO {
	// Fields
	private String gcid;		// graphic card id
	private String gcnm;		// graphic card name
	private String gcvd;		// graphic card vendor
	private String gcvs;		// graphic card version
	private double gcvr;		// graphic card vram
		
	// Constructors
	
}
