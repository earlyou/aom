package com.earlyou.aom.contact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ContactBiz;

@SpringBootTest
class ContactDeleteTests {

	@Autowired
	ContactBiz contactbiz;

	@Test
	void contextLoads() {
		
		try {
			contactbiz.remove(3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
