package com.ssafy.board.model.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatMessageDTO {

    private String roomId;
    private String writer;
    private String message;
    
    public ChatMessageDTO(String roomId, String writer, String message) {
        super();
        this.roomId = roomId;
        this.writer = writer;
        this.message = message;
    }

    public ChatMessageDTO() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public String toString() {
        return "ChatMessageDTO [roomId=" + roomId + ", writer=" + writer + ", message=" + message + "]";
    }
    
}