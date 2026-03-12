package com.cm.spring_ai_demo.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service // Hiring the staff!
public class GeminiService {
    
    private final ChatClient chatClient;
    
    // The question is: Do we need 'new ChatClient()'? 
    // The answer is: No! Spring injects it here.
    public GeminiService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
        System.out.println("API KEY IS: " + System.getenv("GEMINI_API_KEY"));
    }

    public String generateResponse(String userPrompt) {
        return this.chatClient.prompt()
                .user(userPrompt)
                .call()
                .content();
    }
    



}





// package com.cm.spring_ai_demo.service;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.ai.chat.client.ChatClient;
// import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
// import org.springframework.ai.chat.memory.ChatMemory;
// import org.springframework.stereotype.Service;

// import static org.springframework.ai.chat.client.advisor.AbstractChatMemoryAdvisor.CHAT_ID_SIZE;

// @Service
// public class GeminiService {

//     private static final Logger logger = LoggerFactory.getLogger(GeminiService.class);
//     private final ChatClient chatClient;
//     private final ChatMemory chatMemory;

//     public GeminiService(ChatClient.Builder builder, ChatMemory chatMemory) {
//         this.chatMemory = chatMemory;
//         this.chatClient = builder
//                 // 1. Give the AI a permanent "System" identity
//                 .defaultSystem("You are CodingMavrick AI, a Senior Java Developer assistant.")
//                 // 2. Attach the Memory Advisor (Keep last 10 messages for token efficiency)
//                 .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
//                 .build();
//     }

//     public String generateResponse(String userId, String message) {
//         logger.info("Thread: {} | Processing Chat for User: {}", Thread.currentThread().getName(), userId);

//         return chatClient.prompt()
//                 .user(message)
//                 // 3. Bind the request to a specific conversation ID
//                 .advisors(a -> a.param(CHAT_ID_SIZE, userId))
//                 .call()
//                 .content();
//     }

//     public void clearHistory(String userId) {
//         chatMemory.clear(userId);
//         logger.info("Memory cleared for User: {}", userId);
//     }
// }
