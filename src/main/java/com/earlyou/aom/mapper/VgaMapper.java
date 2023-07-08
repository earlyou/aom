package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.VgaVO;

@Repository
@Mapper
public interface VgaMapper {
	public void insert(VgaVO obj) throws Exception;
	public void delete(String obj) throws Exception;	
	public void update(VgaVO obj) throws Exception;
	public VgaVO select(String obj) throws Exception;
	public List<VgaVO> selectall() throws Exception;

	public void deleteall() throws Exception;
}
