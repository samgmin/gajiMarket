package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.FileDTO;

public interface FileMapper {
	public int insert(FileDTO file);
	public List<FileDTO> selectFiles(int bno);
	public FileDTO selectOne(int fno);
}
