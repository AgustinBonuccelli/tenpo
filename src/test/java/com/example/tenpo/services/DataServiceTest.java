package com.example.tenpo.services;

import com.example.tenpo.models.objects.DataModel;
import com.example.tenpo.models.objects.PercentageModel;
import com.example.tenpo.models.objects.RequestModel;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.when;


class DataServiceTest {
    @Mock
    HistoricDataService historicDataService;
    @Mock
    PercentageService percentageService;
    @Mock
    WebClient webClient;
    @InjectMocks
    DataService dataService;
    private DataModel dataModel;
    private RequestModel requestModel;
    private Double percentage = 5.5;
    private PercentageModel percentageModel;
    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);
        dataModel = new DataModel();
        dataModel.setFirstNumber(12.5);
        dataModel.setSecondNumber(32.2);
        requestModel = new RequestModel();
        requestModel.setFirstNumber(12.5);
        requestModel.setSecondNumber(32.2);
        percentageModel = new PercentageModel();
        percentageModel.setPercentage(1.0);
    }

    @AfterEach
    public void tearDownServer() {
    }

    @Test
    void saveHistory() throws Exception {
        dataService.saveHistory(dataModel);
    }
    @Test
    void save() throws Exception {
    }

    @Test
    void calculate() {
    }

    @Test
    void getPercentage() {
    }
}