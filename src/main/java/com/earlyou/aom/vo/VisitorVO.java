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
public class VisitorVO {
	// Fields
	private int vid;				/** visitor id */
	private int vip;				/** visitor IP */
	private int vtime;				/** visit datetime */
	private int vrefer;				/** visitor 접속 사이트 경로 */
	private int vagent;				/** visitor browser */
		
	// Constructors
	
}
