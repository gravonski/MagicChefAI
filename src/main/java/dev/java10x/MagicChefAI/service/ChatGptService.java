package dev.java10x.MagicChefAI.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private String apiKey = System.getenv("OPENAI_API_KEY");

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

  /*
    * curl https://api.openai.com/v1/responses \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer $OPENAI_API_KEY" \
    -d '{
        "model": "gpt-5.2",
        "input": "Write a short bedtime story about a unicorn."
    }'
  */

    public Mono<String> generateRecipe() {
        String prompt = "Agora você é um chef de cozinha, que me entregará receitas com base nos ingredientes fornecidos a você: ";
    }
}
