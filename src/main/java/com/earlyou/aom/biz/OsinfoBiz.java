package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.OsinfoMapper;
import com.earlyou.aom.vo.OsinfoVO;

@Service
public class OsinfoBiz implements Biz<String, OsinfoVO> {

	@Autowired
	OsinfoMapper dao;
	
	@Override
	public void register(OsinfoVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(OsinfoVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public OsinfoVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<OsinfoVO> get() throws Exception {
		return dao.selectall();
	}

	public void removeall() throws Exception {
		dao.deleteall();
	}
}
