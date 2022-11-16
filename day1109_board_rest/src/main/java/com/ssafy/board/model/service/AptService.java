package com.ssafy.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.board.model.dao.AptMapper;
import com.ssafy.board.model.dto.HousedealinfoDTO;

@Service
public class AptService {

	@Autowired
	private AptMapper aMapper;
	
    public List<String> getSidoNames(){
        return aMapper.selectSidoName();
    }
    
    public List<String> getgugunNames(String sidoName){
        return aMapper.selectGugunName(sidoName);
    }
    
    public List<String> getDongNames(String sidoName,String gugunName){
        return aMapper.selectDongName(sidoName, gugunName);
    }
    public String getDongNameByDongCode(String dongCode) {
        return aMapper.selectDongNameByDongCode(dongCode);
    }
    public List<HousedealinfoDTO> getsearchList(String dongName,String dongCode,int dealYear,int dealMonth){
        return aMapper.selectList(dongName, dongCode, dealYear, dealMonth);
    }
    public String getDongCode(String dongName,String sidoName){
        return aMapper.selectDongcode(dongName, sidoName);
    }
    public HousedealinfoDTO getdetail(int dno) {
    	return aMapper.selectDealinfo(dno);
    }
}
