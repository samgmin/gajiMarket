package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.CommentDTO;

public interface CommentMapper {
	public int insert(CommentDTO comment);
	public List<CommentDTO> selectList(int bno);
}
