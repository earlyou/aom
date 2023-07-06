package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.ProjectVO;

@Repository
@Mapper
public interface ProjectMapper {
	public void insert(ProjectVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(ProjectVO obj) throws Exception;
	public ProjectVO select(String obj) throws Exception;
	public List<ProjectVO> selectall() throws Exception;
}
