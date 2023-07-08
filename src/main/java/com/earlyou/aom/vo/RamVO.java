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
public class RamVO {
	// Fields
	private String mbank;		// memory bank
	private double mcapa;		// memory capacity
	private double mclock;		// memory clock
	private String mmfr;		// memory manufacturer
	private String mtype;		// memeory type
		
	// Constructors
	
}
