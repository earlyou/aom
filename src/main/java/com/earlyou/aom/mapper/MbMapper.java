package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.MbVO;

@Repository
@Mapper
public interface MbMapper {
	public void insert(MbVO obj) throws Exception;
	public void delete(String obj) throws Exception;	
	public void update(MbVO obj) throws Exception;
	public MbVO select(String obj) throws Exception;
	public List<MbVO> selectall() throws Exception;

	public void deleteall() throws Exception;
}
