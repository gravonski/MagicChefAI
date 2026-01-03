package dev.java10x.MagicChefAI.service;

import dev.java10x.MagicChefAI.model.FoodItem;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final WebClient webClient;

    @Value("${openai.api.key}")
    private String apiKey;

    public ChatGptService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> generateRecipe(List<FoodItem> foodItems) {

        String alimentos = foodItems.stream()
                .map(item -> String.format("%s (%s) - Quantidade: %d, Validade: %s",
                item.getNome(),item.getCategoria(), item.getQuantidade(), item.getValidade()))
                .collect(Collectors.joining("\n"));
                ;

        String prompt = "Com base no meu banco de dados me sugira uma receita com os seguinte itens, levando em conta a data de validade:\n " + alimentos;

        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey)
                .bodyValue(Map.of(
                        "model", "gpt-5.2",
                        "input", prompt
                ))
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                    try {
                        List<Map<String, Object>> outputList = (List<Map<String, Object>>) response.get("output");
                        if (outputList != null && !outputList.isEmpty()) {
                            Map<String, Object> firstMessage = outputList.get(0);
                            List<Map<String, Object>> contentList = (List<Map<String, Object>>) firstMessage.get("content");
                            if (contentList != null && !contentList.isEmpty()) {
                                return contentList.get(0).get("text").toString();
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Erro de parsing: " + e.getMessage());
                        return "Erro ao processar a receita.";
                    }
                    return "Nenhuma receita foi gerada com sucesso!";
                });
    }
}