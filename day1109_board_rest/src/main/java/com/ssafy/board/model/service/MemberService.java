package com.ssafy.board.model.service;

import com.ssafy.board.model.dto.MemberDTO;

public interface MemberService {

	public MemberDTO login(MemberDTO memberDto) throws Exception;
	public MemberDTO userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	
}
