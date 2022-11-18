package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.FileDTO;
import com.ssafy.board.model.dto.MemberFileDTO;

public interface FileMapper {
	public int insert(FileDTO file);
	public List<FileDTO> selectFiles(int bno);
	public FileDTO selectOne(int fno);
	public int mfileinsert(MemberFileDTO file);
}
