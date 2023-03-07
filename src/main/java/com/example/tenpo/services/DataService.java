package com.example.tenpo.services;

import com.example.tenpo.models.objects.RequestModel;
import com.example.tenpo.models.objects.DataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DataService {
    @Autowired
    HistoricDataService historicDataService;
    @Autowired
    PercentageService percentageService;
    private static Double percentage;

    @Async
    public void saveHistory (DataModel data)
    {
        try
        {
            historicDataService.save(data);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }

    public DataModel save(RequestModel data) throws Exception {
        DataModel response = new DataModel();
        response.setFirstNumber(data.getFirstNumber());
        response.setSecondNumber(data.getSecondNumber());
        int i = 1;
        while(i != 3) {
            try {
                Double result = calculate(data, getPercentage());
                response.setPercentage(percentage);
                response.setResult(result);
                break;
            }
            catch(Exception e)
            {
                System.out.println(e);
                if (e.getMessage() == "Percentage service not working") {
                    saveHistory(response);
                    throw new Exception("Percentage service not working");
                }
                i++;
            }
        }
        saveHistory(response);
        return response;
    }
    public Double calculate(RequestModel data, Double percentage) {
        Double result = (data.getFirstNumber() + data.getSecondNumber()) * ( percentage / 100 + 1);
        return result;
    }

    public Double getPercentage() throws Exception {
        try {
            percentage = percentageService.getPercentage().getPercentage();
            return percentageService.getPercentage().getPercentage();
        }
        catch (Exception error) {
            if ( percentage != null )
            {
                return percentage;
            }

            throw new Exception("Percentage service not working");
        }
    }
}
