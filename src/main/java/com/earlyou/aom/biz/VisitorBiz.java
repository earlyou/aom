package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.VisitorMapper;
import com.earlyou.aom.vo.VisitorVO;

@Service
public class VisitorBiz implements Biz<Integer, VisitorVO> {

	@Autowired
	VisitorMapper dao;
	
	@Override
	public void register(VisitorVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(VisitorVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(Integer k) throws Exception {
		dao.delete(k);
	}

	@Override
	public VisitorVO get(Integer k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<VisitorVO> get() throws Exception {
		return dao.selectall();
	}

	public void removeall() throws Exception {
		dao.deleteall();
	}
}
