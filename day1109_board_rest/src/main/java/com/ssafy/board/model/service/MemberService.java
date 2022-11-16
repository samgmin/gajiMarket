package com.ssafy.board.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.MemberMapper;
import com.ssafy.board.model.dto.MemberDTO;

@Service
public class MemberService {
	@Autowired
	private MemberMapper memberDao;
	
	public MemberDTO login(String id, String pw) {
		return memberDao.selectOne(id, pw);
	}
}
