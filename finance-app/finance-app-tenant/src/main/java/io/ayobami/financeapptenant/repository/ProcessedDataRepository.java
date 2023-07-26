package io.ayobami.financeapptenant.repository;


import io.ayobami.financeapptenant.entity.dataProcessor.ProcessedDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ProcessedDataRepository  extends JpaRepository<ProcessedDataEntity, Integer> {
}
