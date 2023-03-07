package com.example.tenpo.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "historic_data")

@Getter
@Setter
public class HistoricDataModel {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(unique = true, nullable = false)
  private Long id;

  private Double firstNumber;
  private Double secondNumber;
  private Double percentage;
  private Double result;

}
