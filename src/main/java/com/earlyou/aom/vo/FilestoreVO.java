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
public class FilestoreVO {
	// Fields
	private String fsmnt;		// FileStore mount
	private double fstot;		// FileStore total space
	private String fstype;		// FileStore type
		
	// Constructors
	
}
