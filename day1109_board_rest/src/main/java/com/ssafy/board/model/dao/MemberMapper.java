package com.ssafy.board.model.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.board.model.dto.MemberDTO;

@Repository
public interface MemberMapper {
	public MemberDTO selectOne(@Param("id")String id, @Param("pw")String pw);
}
