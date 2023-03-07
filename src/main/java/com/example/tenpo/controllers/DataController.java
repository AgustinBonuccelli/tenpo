package com.example.tenpo.controllers;

import com.example.tenpo.models.entities.HistoricDataModel;
import com.example.tenpo.models.objects.DataModel;
import com.example.tenpo.models.objects.RequestModel;
import com.example.tenpo.services.DataService;
import com.example.tenpo.services.HistoricDataService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;
    @Autowired
    HistoricDataService historicDataService;

    private final Bucket bucket;
    public DataController() {
        Bandwidth limit = Bandwidth.classic(3, Refill.greedy(3, Duration.ofMinutes(1)));
        this.bucket = Bucket4j.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping()
    public ResponseEntity calculate(@Valid @RequestBody RequestModel data) throws Exception {
        if (bucket.tryConsume(1)){
            DataModel result = dataService.save(data);
            return ResponseEntity.ok(result);
        }
        return new ResponseEntity("To many requests, please try later", HttpStatus.TOO_MANY_REQUESTS);
    }

    @GetMapping("/{pageNumber}/{pageSize}/{sort}")
    public ResponseEntity getHistoricData (@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String sort) {
        if (bucket.tryConsume(1)){
            Page<HistoricDataModel> data = historicDataService.getHistoricData(pageNumber, pageSize, sort);
            return ResponseEntity.ok(data.getContent());
        }
        return new ResponseEntity("To many requests, please try later", HttpStatus.TOO_MANY_REQUESTS);
    }

}
