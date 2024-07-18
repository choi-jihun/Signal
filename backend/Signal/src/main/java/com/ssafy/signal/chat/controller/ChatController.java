package com.ssafy.signal.chat.controller;

import com.ssafy.signal.chat.domain.ChatRoomDto;
import com.ssafy.signal.chat.domain.ChatRoomEntity;
import com.ssafy.signal.chat.domain.MessageDto;
import com.ssafy.signal.chat.repository.ChatRoomRepository;
import com.ssafy.signal.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/send")
    public void send(MessageDto message) throws Exception {
        chatService.saveMessage(message);
        messagingTemplate.convertAndSend("/topic/" + message.getChat_id(), message);
    }

    @PostMapping("/chat-room/create")
    public ChatRoomDto createChatRoom(@RequestBody ChatRoomDto chatRoomDto) throws Exception {
        return chatService.createChatRoom(chatRoomDto);
    }

    @GetMapping("/chat-room")
    public List<ChatRoomDto> getAllChatRooms(@RequestParam("user_id") long user_id) throws Exception {
        return chatService.getAllChatRooms(user_id);
    }

    @GetMapping("/chat-room/messages")
    public List<MessageDto> getAllMessage(@RequestParam("chat_id") long chat_id) throws Exception {
        return chatService.getAllMessages(chat_id);
    }
}
