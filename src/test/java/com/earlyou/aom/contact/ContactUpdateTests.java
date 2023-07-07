package com.earlyou.aom.contact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ContactBiz;
import com.earlyou.aom.vo.ContactVO;

@SpringBootTest
class ContactUpdateTests {

	@Autowired
	ContactBiz contactbiz;

	@Test
	void contextLoads() {
		ContactVO cont = new ContactVO(2, "contact test id", "update pwd", "updated name", "update@test.com", 82, "01010101010", "updated message");
		
		try {
			contactbiz.modify(cont);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
