package com.ssafy.board.model.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.FileMapper;
import com.ssafy.board.model.dao.MemberMapper;
import com.ssafy.board.model.dto.FileDTO;
import com.ssafy.board.model.dto.MemberDTO;
import com.ssafy.board.model.dto.MemberFileDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private FileMapper fdao;
	
	@Override
	public MemberDTO login(MemberDTO MemberDTO) throws Exception {
		if (MemberDTO.getUserid() == null || MemberDTO.getUserpwd() == null)
			return null;
		return sqlSession.getMapper(MemberMapper.class).login(MemberDTO);
	}

	@Override
	public MemberDTO userInfo(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		sqlSession.getMapper(MemberMapper.class).saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return sqlSession.getMapper(MemberMapper.class).getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		sqlSession.getMapper(MemberMapper.class).deleteRefreshToken(map);
	}

	@Override
	public MemberDTO userSignup(MemberDTO memberDto) throws Exception {
		sqlSession.getMapper(MemberMapper.class).userSignup(memberDto);
		return memberDto;
	}
	
	@Override
	public int addFile(MemberFileDTO file) {
		return fdao.mfileinsert(file);
	}

}
