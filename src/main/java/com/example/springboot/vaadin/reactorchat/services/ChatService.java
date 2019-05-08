package com.example.springboot.vaadin.reactorchat.services;

import com.example.springboot.vaadin.reactorchat.domain.ChatMessage;

import java.util.function.Consumer;

public interface ChatService {
    public void onNext(ChatMessage chatMessage);
    public void subscribe(Consumer<? super ChatMessage> onNext);
}