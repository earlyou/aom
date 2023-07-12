package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.OsinfoVO;

@Repository
@Mapper
public interface OsinfoMapper {
	public void insert(OsinfoVO obj) throws Exception;
	public void delete(String obj) throws Exception;
	public void update(OsinfoVO obj) throws Exception;
	public OsinfoVO select(String obj) throws Exception;
	public List<OsinfoVO> selectall() throws Exception;
	public void deleteall() throws Exception;
}
