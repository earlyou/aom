package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.RamVO;

@Repository
@Mapper
public interface RamMapper {
	public void insert(RamVO obj) throws Exception;
	public void delete(String obj) throws Exception;	
	public void update(RamVO obj) throws Exception;
	public RamVO select(String obj) throws Exception;
	public List<RamVO> selectall() throws Exception;

	public void deleteall() throws Exception;
}
