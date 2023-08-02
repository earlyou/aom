package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.VisitorVO;

@Repository
@Mapper
public interface VisitorMapper {
	public void insert(VisitorVO obj) throws Exception;
	public void delete(int obj) throws Exception;	
	public void update(VisitorVO obj) throws Exception;
	public VisitorVO select(int obj) throws Exception;
	public List<VisitorVO> selectall() throws Exception;

	public void deleteall() throws Exception;
}
