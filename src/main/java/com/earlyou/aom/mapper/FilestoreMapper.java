package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.FilestoreVO;

@Repository
@Mapper
public interface FilestoreMapper {
	public void insert(FilestoreVO obj) throws Exception;
	public void delete(String obj) throws Exception;	
	public void update(FilestoreVO obj) throws Exception;
	public FilestoreVO select(String obj) throws Exception;
	public List<FilestoreVO> selectall() throws Exception;

	public void deleteall() throws Exception;
}
