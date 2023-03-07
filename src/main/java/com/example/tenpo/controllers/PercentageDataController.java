package com.example.tenpo.controllers;

import com.example.tenpo.services.HistoricDataService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Random;

@RestController
@RequestMapping("/percentage")
public class PercentageDataController {
    @Autowired
    HistoricDataService historicDataService;

    @GetMapping()
    public ResponseEntity getPercentage(){
        try {
            Random r = new Random();
            Double min = 1.0;
            Double max = 100.0;
            Double randomValue = min + (max - min) * r.nextDouble();
            HashMap<String, Double> map = new HashMap<>();
            map.put("percentage", randomValue);
            return ResponseEntity.ok(map);
        }
        catch (Exception e)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
