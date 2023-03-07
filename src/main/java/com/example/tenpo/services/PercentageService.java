package com.example.tenpo.services;

import com.example.tenpo.models.objects.PercentageModel;
import com.example.tenpo.repositories.PercentageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@CacheConfig(cacheNames = {"percentages"})
@Service
public class PercentageService implements PercentageRepository{
    private final WebClient webClient;

    @Autowired
    public PercentageService (WebClient.Builder builder, @Value("${tenpo.url}") String usersBaseUrl) {
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .baseUrl(usersBaseUrl)
                .build();
    }
    @Cacheable
    public PercentageModel getPercentage()
    {
        return webClient.get()
                .uri("/percentage")
                .retrieve()
                .bodyToMono(PercentageModel.class).block();
    }

}
