package com.example.tenpo.services;

import com.example.tenpo.models.objects.PercentageModel;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.After;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PercentageServiceTest {
    @InjectMocks
    PercentageService percentageService;
    @Mock
    WebClient webClient;
    private MockWebServer mockWebServer;
    private Double percentageMock = 1.0;
    private PercentageModel percentageModel;
    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        this.mockWebServer = new MockWebServer();
        this.mockWebServer.start();
        this.percentageService = new PercentageService(WebClient.builder(), mockWebServer.url("/").toString());
        percentageModel = new PercentageModel();
        percentageModel.setPercentage(1.0);
    }
    @After
    void afterAll() throws IOException {
        this.mockWebServer.shutdown();
    }

    @Test
    void getPercentage() {
        MockResponse mockResponse = new MockResponse()
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .setBody("{\"percentage\":\"" + percentageMock + "\"}");
        mockWebServer.enqueue(mockResponse);
        PercentageModel percentage = percentageService.getPercentage();
        assertNotNull(percentage);
        assertEquals(percentageMock,percentage.getPercentage());

    }
}