package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.AdminMapper;
import com.earlyou.aom.vo.AdminVO;

@Service
public class AdminBiz implements Biz<String, AdminVO> {

	@Autowired
	AdminMapper dao;
	
	@Override
	public void register(AdminVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(AdminVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public AdminVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<AdminVO> get() throws Exception {
		return dao.selectall();
	}

}
