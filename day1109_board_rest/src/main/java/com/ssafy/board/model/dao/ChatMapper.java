package com.ssafy.board.model.dao;

import java.util.List;

import com.ssafy.board.model.dto.ChatMessageDTO;
import com.ssafy.board.model.dto.ChatRoomDTO;

public interface ChatMapper {

    List<ChatRoomDTO> selectRoomList();
    ChatRoomDTO selectRoomOne(String id);
    boolean insertRoom(ChatRoomDTO room);
    boolean insertMessage(ChatMessageDTO message);

}