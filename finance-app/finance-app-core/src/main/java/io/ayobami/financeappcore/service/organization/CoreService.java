package io.ayobami.financeappcore.service.organization;

import io.ayobami.financeappcore.entity.organization.DataSource;
import io.ayobami.financeappcore.entity.organization.OrganizationDTO;
import io.ayobami.financeappcore.entity.ResponseBody;

import java.util.List;

public interface CoreService {

    ResponseBody registerTenantDataSource(OrganizationDTO orgDTO, String tenant);
    List<DataSource> getAllTenants();
}
