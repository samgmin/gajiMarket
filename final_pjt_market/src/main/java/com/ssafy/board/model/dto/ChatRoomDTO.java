package com.ssafy.board.model.dto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
public class ChatRoomDTO {

    private String roomId;
    private String name;
    private String roomMaker;
    private String description;
    //WebSocketSession은 Spring에서 Websocket Connection이 맺어진 세션

//    public static ChatRoomDTO create(String name){
//        ChatRoomDTO room = new ChatRoomDTO();
//
//        room.roomId = UUID.randomUUID().toString();
//        room.name = name;
//        return room;
//    }
    public ChatRoomDTO() {}
    public ChatRoomDTO( String name, String roomMaker, String description) {
        super();
        this.roomId = UUID.randomUUID().toString();
        this.name = name;
        this.roomMaker = roomMaker;
        this.description = description;
    }
    @Override
    public String toString() {
        return "ChatRoomDTO [roomId=" + roomId + ", name=" + name + ", roomMaker=" + roomMaker + ", description="
                + description + "]";
    }
    
}