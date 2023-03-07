package com.example.tenpo.repositories;

import com.example.tenpo.models.entities.HistoricDataModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricDataRepository extends CrudRepository<HistoricDataModel, Long> {
    Page<HistoricDataModel> findAll(Pageable pageable);
}
