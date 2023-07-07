package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.CpuMapper;
import com.earlyou.aom.vo.CpuVO;

@Service
public class CpuBiz implements Biz<String, CpuVO> {

	@Autowired
	CpuMapper dao;
	
	@Override
	public void register(CpuVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(CpuVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public CpuVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<CpuVO> get() throws Exception {
		return dao.selectall();
	}

	public void removeall() throws Exception {
		dao.deleteall();
	}
}
