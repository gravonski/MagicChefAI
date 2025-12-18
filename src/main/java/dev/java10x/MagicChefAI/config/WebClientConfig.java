package dev.java10x.MagicChefAI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("openai.api.url")
    private String chatGptUrl;

    @Value("openai.api.key")
    private String chatGptKey;

    public WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(chatGptUrl).build();
    }

}


