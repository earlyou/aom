package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.RamMapper;
import com.earlyou.aom.vo.RamVO;

@Service
public class RamBiz implements Biz<String, RamVO> {

	@Autowired
	RamMapper dao;
	
	@Override
	public void register(RamVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(RamVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public RamVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<RamVO> get() throws Exception {
		return dao.selectall();
	}

	public void removeall() throws Exception {
		dao.deleteall();
	}
}
