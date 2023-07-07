package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.CpuVO;

@Repository
@Mapper
public interface CpuMapper {
	public void insert(CpuVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(CpuVO obj) throws Exception;
	public CpuVO select(String obj) throws Exception;
	public List<CpuVO> selectall() throws Exception;

	public void deleteall() throws Exception;
}
