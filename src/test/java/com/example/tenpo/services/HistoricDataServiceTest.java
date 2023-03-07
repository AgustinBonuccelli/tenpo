package com.example.tenpo.services;

import com.example.tenpo.models.entities.HistoricDataModel;
import com.example.tenpo.models.objects.DataModel;
import com.example.tenpo.models.objects.PercentageModel;
import com.example.tenpo.repositories.HistoricDataRepository;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class HistoricDataServiceTest {
    @InjectMocks
    HistoricDataService historicDataService;
    @Mock
    HistoricDataRepository historicDataRepository;
    private DataModel dataModel;
    private HistoricDataModel historicDataModel;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dataModel = new DataModel();
        dataModel.setFirstNumber(12.5);
        dataModel.setSecondNumber(32.2);
        dataModel.setPercentage(1.0);
        dataModel.setResult(1.0);
        historicDataModel = new HistoricDataModel();
        historicDataModel.setId(1L);
        historicDataModel.setFirstNumber(1.0);
        historicDataModel.setSecondNumber(1.0);
        historicDataModel.setPercentage(1.0);
        historicDataModel.setResult(1.0);
    }

    @Test
    void getHistoricData() {

    }

    @Test
    void save() {
        when(historicDataRepository.save(historicDataModel)).thenReturn(historicDataModel);
        HistoricDataModel result = historicDataRepository.save(historicDataModel);
        assertNotNull(result);
        assertEquals(result.getResult(),dataModel.getResult());
    }
}