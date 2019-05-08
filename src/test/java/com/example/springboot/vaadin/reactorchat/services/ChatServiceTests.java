package com.example.springboot.vaadin.reactorchat.services;

import com.example.springboot.vaadin.reactorchat.domain.ChatMessage;
import com.example.springboot.vaadin.reactorchat.domain.ChatMessage;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatServiceTests {
    private final String CHAT_MSG_SEND = "Welcome to the Chat!";
    private final String USER_ADMIN = "Admin";

    private ChatService chatService;
    private List<ChatMessage> chatMessageList;
    private UnicastProcessor<ChatMessage> publisher;
    private Flux<ChatMessage> chatMessageFlux;

    @Before
    public void setup(){
        publisher = UnicastProcessor.create();
        chatMessageFlux = publisher.replay(30).autoConnect();
        chatService = new ChatServiceImpl(publisher, chatMessageFlux);
        chatMessageList = new ArrayList<>();
    }

    @Test
    public void givenChatMsg_whenChatMsgSend_ThenTheReceiveChatMsgIsTheSame(){

        // given a chat message
        ChatMessage chatMessageSend = new ChatMessage(LocalDateTime.now(), USER_ADMIN, CHAT_MSG_SEND);

        // when the chat message is send
        chatService.onNext(chatMessageSend);

        // then the receive Chat message is the same one
        chatService.subscribe(chatMessageList::add);
        ChatMessage chatMessageRecive = chatMessageList.get(0);

        assertEquals(chatMessageSend, chatMessageRecive);
    }

}
