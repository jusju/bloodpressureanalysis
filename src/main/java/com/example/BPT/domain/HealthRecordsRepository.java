package com.example.BPT.domain;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface HealthRecordsRepository extends CrudRepository<HealthRecords, Long> {

	List<HealthRecords> findById(long Id);

	List<HealthRecords> findByDate(LocalDate date);

	List<HealthRecords> findByUsername(@Param("username") String username);
}
