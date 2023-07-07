package com.earlyou.aom.contact;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.earlyou.aom.biz.ContactBiz;
import com.earlyou.aom.vo.ContactVO;

@SpringBootTest
class ContactReadAllTests {
	
	@Autowired
	ContactBiz contactbiz;

	@Test
	void contextLoads() {
		
		List<ContactVO> list = null;
		
		try {
			list = contactbiz.get();
			for (ContactVO cont : list) {
				System.out.println(cont);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}