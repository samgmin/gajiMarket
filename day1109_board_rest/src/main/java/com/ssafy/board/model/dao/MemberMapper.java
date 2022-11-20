package com.ssafy.board.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.board.model.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	public MemberDTO login(MemberDTO memberDto) throws SQLException;
    public int checkId(String userid);
    public int checkName(String username);
    public int delete(String userid);
    public int userSignup(MemberDTO memberDto) throws SQLException;
    public int userModify(MemberDTO memberDto) throws SQLException;
//    public int imageModify(MemberDTO memberDto) throws SQLException;
	public MemberDTO userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	
}
