package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.MbMapper;
import com.earlyou.aom.vo.MbVO;

@Service
public class MbBiz implements Biz<String, MbVO> {

	@Autowired
	MbMapper dao;
	
	@Override
	public void register(MbVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(MbVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public MbVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<MbVO> get() throws Exception {
		return dao.selectall();
	}

	public void removeall() throws Exception {
		dao.deleteall();
	}
}
