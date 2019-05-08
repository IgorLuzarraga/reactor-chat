package com.example.springboot.vaadin.reactorchat.services;

import com.example.springboot.vaadin.reactorchat.domain.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.util.function.Consumer;

@Service
public class ChatServiceImpl implements ChatService {

    private final UnicastProcessor<ChatMessage> publisher;
    private final Flux<ChatMessage> chatMessageFlux;

    @Autowired
    public ChatServiceImpl(UnicastProcessor<ChatMessage> publisher,
                           Flux<ChatMessage> chatMessageFlux) {
        this.publisher = publisher;
        this.chatMessageFlux = chatMessageFlux;
    }

    @Override
    public void onNext(ChatMessage chatMessage){
        publisher.onNext(chatMessage);
    }

    @Override
    public void subscribe(Consumer<? super ChatMessage> onNext) {
        chatMessageFlux.subscribe(onNext);
    }
}
