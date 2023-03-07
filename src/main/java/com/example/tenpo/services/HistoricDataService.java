package com.example.tenpo.services;

import com.example.tenpo.models.entities.HistoricDataModel;
import com.example.tenpo.models.objects.DataModel;
import com.example.tenpo.repositories.HistoricDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;

@Service
@EnableAsync
public class HistoricDataService {
    HistoricDataRepository historicDataRepository;
    @Autowired
    public HistoricDataService(HistoricDataRepository historicDataRepository){
        this.historicDataRepository = historicDataRepository;
    }
    public Page<HistoricDataModel> getHistoricData(Integer pageNumber, Integer pageSize, String sort) {
        Pageable pageable = null;
        if (sort != null) {
            // with sorting
            pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.ASC, sort);
        } else {
            // without sorting
            pageable = PageRequest.of(pageNumber, pageSize);
        }
        return historicDataRepository.findAll(pageable);
    }

    public HistoricDataModel save(DataModel data){
        HistoricDataModel newData = new HistoricDataModel();
        newData.setFirstNumber(data.getFirstNumber());
        newData.setSecondNumber((data.getSecondNumber()));
        newData.setPercentage(data.getPercentage());
        newData.setResult(data.getResult());
        return historicDataRepository.save(newData);
    }
}
