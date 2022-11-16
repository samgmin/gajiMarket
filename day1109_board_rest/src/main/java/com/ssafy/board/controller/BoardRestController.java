package com.ssafy.board.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.board.model.dto.BoardDTO;
import com.ssafy.board.model.dto.BoardParameterDto;
import com.ssafy.board.model.dto.CommentDTO;
import com.ssafy.board.model.service.BoardService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/board")
public class BoardRestController {
	
	@Autowired
	private BoardService bservice;
		
	@GetMapping
	public ResponseEntity<Map<String, Object>> list(@RequestParam(value = "page", defaultValue = "1") int page) {
		return new ResponseEntity<Map<String, Object>>(bservice.makePage(page), HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/list")
	public ResponseEntity<Map<String, Object>> list(@RequestBody BoardParameterDto boardParameterDto) {
		return new ResponseEntity<Map<String, Object>>(bservice.makePage(boardParameterDto), HttpStatus.ACCEPTED);
	}
	
	@PostMapping
//	public ResponseEntity<String> write(BoardDTO board, MultipartFile[] uploadFile) throws IllegalStateException, IOException {
	public ResponseEntity<String> write(@RequestBody BoardDTO board) throws IllegalStateException, IOException {
		System.out.println("글 쓰기에 왔어요");
		// 일단 글이 DB에 저장되야 글번호를 파일 업로드에 넣을 수 있음
		System.out.println("before write: " + board);
		boolean writeResult = bservice.write(board);
		System.out.println("after write: " + board);
		
		// 글쓰기에 파일 첨부 기능 추가
//		if(uploadFile != null && uploadFile.length > 0) {
//			String uploadPath = "c:/SSAFY/upload";
//			File uploadDir = new File(uploadPath);
//			
//			if(!uploadDir.exists()) { // 업로드 파일 저장 폴더 없으면 생성
//				uploadDir.mkdir();
//			}
//			
//			for(MultipartFile file : uploadFile) { // 파일 개수만큼 반복
//				String savedName = new Random().nextInt(1000000000) + "";
//				File savedFile = new File(uploadPath + "/" + savedName);
//				file.transferTo(savedFile); // profile.png -> c:/SSAFY/upload/2145346434
//				
//				FileDTO dto = new FileDTO(board.getBno(), file.getOriginalFilename(), savedFile.getAbsolutePath());
//				bservice.addFile(dto);
//			}
//		}
		
		if(writeResult) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/read")
	public ResponseEntity<Map<String, Object>> read(int bno) {
		return new ResponseEntity<Map<String, Object>>(bservice.makeRead(bno), HttpStatus.OK);
	}
	
	@PutMapping("/modify")
	public ResponseEntity<String> modify(@RequestBody BoardDTO board) {
		System.out.println(board);
		if(bservice.update(board)) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{bno}")
	public ResponseEntity<String> delete(@PathVariable("bno") int bno) {
		System.out.println("오긴해?");
		if(bservice.delete(bno)) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PostMapping("/comment")
	public ResponseEntity<Map<String, Object>> comment(@RequestBody CommentDTO comment) {
		Map<String, Object> result = new HashMap<String, Object>();;
		System.out.println(comment);
		if(bservice.writeComment(comment)) {
			List<CommentDTO> list = bservice.getComments(comment.getBno());
			System.out.println(list);
			result.put("cList", list);
			result.put("msg", "success");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.ACCEPTED);
		} else {
			result.put("msg", "error");
			return new ResponseEntity<Map<String, Object>>(result, HttpStatus.BAD_REQUEST);
		}
	}
	
}
