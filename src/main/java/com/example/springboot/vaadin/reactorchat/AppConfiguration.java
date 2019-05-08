package com.example.springboot.vaadin.reactorchat;

import com.example.springboot.vaadin.reactorchat.domain.ChatMessage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.UnicastProcessor;

import java.time.LocalDateTime;

@Configuration
public class AppConfiguration {
    private final String CHAT_MSG_1 = "Welcome to the Chat!";
    private final String USER_ADMIN = "Admin";

    @Bean
    UnicastProcessor<ChatMessage> publisher(){
        UnicastProcessor<ChatMessage> publisher = UnicastProcessor.create();

        // send the welcome msg
        publisher.onNext(new ChatMessage(LocalDateTime.now(), USER_ADMIN, CHAT_MSG_1));

        return  publisher;
    }
    @Bean
    Flux<ChatMessage> chatMessageFlux(UnicastProcessor<ChatMessage> publisher) {
        return publisher.replay(30).autoConnect();
    }
}