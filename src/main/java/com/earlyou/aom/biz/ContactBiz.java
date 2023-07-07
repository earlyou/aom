package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.ContactMapper;
import com.earlyou.aom.vo.ContactVO;

@Service
public class ContactBiz implements Biz<Integer, ContactVO> {

	@Autowired
	ContactMapper dao;
	
	@Override
	public void register(ContactVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(ContactVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public ContactVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<ContactVO> get() throws Exception {
		return dao.selectall();
	}

}
