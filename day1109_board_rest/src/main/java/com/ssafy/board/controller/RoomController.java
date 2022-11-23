package com.ssafy.board.controller;

import com.ssafy.board.model.dto.ChatRoomDTO;
import com.ssafy.board.model.service.ChatRoomService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
@Log4j2
public class RoomController {

    @Autowired
    private ChatRoomService cservice;

    //채팅방 목록 조회
    @GetMapping("/rooms")
    public List<ChatRoomDTO> rooms(){
        log.info("# All Chat Rooms");
        return cservice.findAllRooms();
    }

    //채팅방 개설
    @PostMapping("/room")
    public String create(@RequestParam String name){
        log.info("# Create Chat Room , name: " + name);
        return cservice.createChatRoomDTO(name);
    }

    //채팅방 조회
    @GetMapping("/room")
    public ChatRoomDTO getRoom(String roomId){
        log.info("# get Chat Room, roomID : " + roomId);
        return cservice.findRoomById(roomId);
    }
}