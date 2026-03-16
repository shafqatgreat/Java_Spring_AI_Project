package com.cm.spring_ai_demo.config;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemoryRepository; // Updated class name
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {

    @Bean
    public ChatMemory chatMemory() {
        // High-speed RAM storage for the current session
        // 1. Create the storage repository
        var repository = new InMemoryChatMemoryRepository();
        // 2. Wrap it in a ChatMemory implementation (Logic)
        // This keeps a 'window' of the last 10 messages
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(repository)
                .maxMessages(10) 
                .build();
    }
    
}
