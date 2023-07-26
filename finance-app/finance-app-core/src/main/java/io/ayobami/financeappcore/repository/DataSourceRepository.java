package io.ayobami.financeappcore.repository;

import io.ayobami.financeappcore.entity.organization.DataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface DataSourceRepository  extends JpaRepository<DataSource, Integer> {
}
