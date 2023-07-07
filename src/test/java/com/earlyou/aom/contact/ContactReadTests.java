package com.earlyou.aom.contact;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ContactBiz;
import com.earlyou.aom.vo.ContactVO;

@SpringBootTest
class ContactReadTests {

	@Autowired
	ContactBiz contactbiz;

	@Test
	void contextLoads() {
		
		ContactVO cont = null;
		
		try {
			cont = contactbiz.get(2);
			System.out.println(cont);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
