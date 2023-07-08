package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.VgaMapper;
import com.earlyou.aom.vo.VgaVO;

@Service
public class VgaBiz implements Biz<String, VgaVO> {

	@Autowired
	VgaMapper dao;
	
	@Override
	public void register(VgaVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(VgaVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public VgaVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<VgaVO> get() throws Exception {
		return dao.selectall();
	}

	public void removeall() throws Exception {
		dao.deleteall();
	}
}
