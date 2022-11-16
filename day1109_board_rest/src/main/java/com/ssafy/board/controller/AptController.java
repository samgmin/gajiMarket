package com.ssafy.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.HousedealinfoDTO;
import com.ssafy.board.model.service.AptService;

@RestController
@RequestMapping("/apt")
public class AptController {
	@Autowired
	private AptService aService;
	
	@GetMapping("/sido")
	public ResponseEntity<List<String>> sidoList(){
		return new ResponseEntity<List<String>>(aService.getSidoNames(),HttpStatus.ACCEPTED);
	}
	@GetMapping("/gugun")
	public ResponseEntity<List<String>> gugunList(String sidoName){
		return new ResponseEntity<List<String>>(aService.getgugunNames(sidoName),HttpStatus.ACCEPTED);

	}
	@GetMapping("/dong")
	public ResponseEntity<List<String>> dongList(String sidoName, String gugunName){
		return new ResponseEntity<List<String>>(aService.getDongNames(sidoName, gugunName),HttpStatus.ACCEPTED);
	}
	@GetMapping("/search")
	public ResponseEntity<List<HousedealinfoDTO>> search(String sido, String dong,int dealYear,int dealMonth){
		System.out.println(sido+" "+dong+" "+dealYear+" "+dealMonth);
		String dongCode = aService.getDongCode(dong, sido);
		System.out.println(dongCode);
		List<HousedealinfoDTO> a = aService.getsearchList(dong, dongCode, dealYear, dealMonth);
		System.out.println(a);
		return new ResponseEntity<List<HousedealinfoDTO>>(aService.getsearchList(dong, dongCode, dealYear, dealMonth),HttpStatus.ACCEPTED);
	}
	@GetMapping("/detail")
	public ResponseEntity<HousedealinfoDTO> search(int dno){
		System.out.println(aService.getdetail(dno));
		return new ResponseEntity<HousedealinfoDTO>(aService.getdetail(dno),HttpStatus.ACCEPTED);
	}

}
