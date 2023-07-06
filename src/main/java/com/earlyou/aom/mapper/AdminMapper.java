package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.AdminVO;

@Repository
@Mapper
public interface AdminMapper {
	public void insert(AdminVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(AdminVO obj) throws Exception;
	public AdminVO select(String obj) throws Exception;
	public List<AdminVO> selectall() throws Exception;
}
