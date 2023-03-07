package com.example.tenpo.models.objects;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestModel {
    @NotNull(message = "The firstNumber is required")
    private Double firstNumber;
    @NotNull(message = "The secondNumber is required")
    private Double secondNumber;

}
