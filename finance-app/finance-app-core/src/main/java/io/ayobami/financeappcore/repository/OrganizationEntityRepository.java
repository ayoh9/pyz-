package io.ayobami.financeappcore.repository;

import io.ayobami.financeappcore.entity.organization.OrganizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface OrganizationEntityRepository extends JpaRepository<OrganizationEntity, Integer> {

    OrganizationEntity findByInstanceName(String instanceName);
}
