package io.ayobami.financeappcore.web;

import io.ayobami.financeappcore.entity.organization.DataSource;
import io.ayobami.financeappcore.entity.organization.OrganizationDTO;
import io.ayobami.financeappcore.entity.ResponseBody;
import io.ayobami.financeappcore.service.organization.CoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class CoreController {

    private final CoreService coreService;

    @PostMapping("/addsource/{tenant}")
    public ResponseEntity<String> addDSource(@RequestBody OrganizationDTO orgDTO, @PathVariable("tenant") String tenant) {
        ResponseBody response = coreService.registerTenantDataSource(orgDTO, tenant);
        return ResponseEntity.status(response.getStatusCode()).body(response.getMessage());
    }

    @GetMapping("/getAll")
    public List<DataSource> getAll() {
        return coreService.getAllTenants();
    }
}
