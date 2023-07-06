package com.earlyou.aom.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.earlyou.aom.frame.Biz;
import com.earlyou.aom.mapper.ProjectMapper;
import com.earlyou.aom.vo.ProjectVO;

@Service
public class ProjectBiz implements Biz<String, ProjectVO> {

	@Autowired
	ProjectMapper dao;
	
	@Override
	public void register(ProjectVO v) throws Exception {
		dao.insert(v);
	}

	@Override
	public void modify(ProjectVO v) throws Exception {
		dao.update(v);
	}

	@Override
	public void remove(String k) throws Exception {
		dao.delete(k);
	}

	@Override
	public ProjectVO get(String k) throws Exception {
		return dao.select(k);
	}

	@Override
	public List<ProjectVO> get() throws Exception {
		return dao.selectall();
	}

}
