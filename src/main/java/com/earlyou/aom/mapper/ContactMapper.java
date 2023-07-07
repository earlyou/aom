package com.earlyou.aom.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.earlyou.aom.vo.ContactVO;

@Repository
@Mapper
public interface ContactMapper {
	public void insert(ContactVO obj) throws Exception;
	public void delete(int obj) throws Exception;
	public void update(ContactVO obj) throws Exception;
	public ContactVO select(int obj) throws Exception;
	public List<ContactVO> selectall() throws Exception;
}
