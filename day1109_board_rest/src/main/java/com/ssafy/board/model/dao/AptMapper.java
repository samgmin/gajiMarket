package com.ssafy.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.board.model.dto.HousedealinfoDTO;

public interface AptMapper {
	public String selectDongcode(@Param("dongName")String dongName, @Param("sidoName")String sidoName);
    public String selectLatlng(String dongName);
    public List<HousedealinfoDTO> selectList(@Param("dongName")String dongName,
    		@Param("dongCode")String dongCode,@Param("dealYear")int dealYear,@Param("dealMonth")int dealMonth);
    public List<String> selectSidoName();// 검색기능
    public List<String> selectGugunName(@Param("sidoName")String sidoName); // 검색기능
    public List<String> selectDongName(@Param("sidoName")String sidoName,@Param("gugunName")String gugunName); // 검색기능
    public String selectDongNameByDongCode(String dongCode);
	public HousedealinfoDTO selectDealinfo(int dno);
    
}
