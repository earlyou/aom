package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.FilestoreMapper;
import com.earlyou.aom.vo.FilestoreVO;

@Service
public class FilestoreBiz implements Biz<String, FilestoreVO> {

	@Autowired
	FilestoreMapper dao;
	
	@Override
	public void register(FilestoreVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(FilestoreVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public FilestoreVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<FilestoreVO> get() throws Exception {
		return dao.selectall();
	}

	public void removeall() throws Exception {
		dao.deleteall();
	}
}
