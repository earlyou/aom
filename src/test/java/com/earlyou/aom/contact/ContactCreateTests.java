package com.earlyou.aom.contact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ContactBiz;
import com.earlyou.aom.vo.ContactVO;

@SpringBootTest
class ContactCreateTests {
	
	@Autowired
	ContactBiz contactbiz;

	@Test
	void contextLoads() {
		ContactVO cont = new ContactVO("testctid", "testpwd", "testctnm", "ctem@test.com", 82, "01023234554", "this is contact test message");
		
		try {
			contactbiz.register(cont);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
