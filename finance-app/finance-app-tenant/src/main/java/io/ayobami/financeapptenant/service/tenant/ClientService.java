package io.ayobami.financeapptenant.service.tenant;

import io.ayobami.financeapptenant.entity.tenant.OrganizationDTO;
import io.ayobami.financeapptenant.entity.ResponseBody;

public interface ClientService {

    ResponseBody register(OrganizationDTO orgDTO);
}
