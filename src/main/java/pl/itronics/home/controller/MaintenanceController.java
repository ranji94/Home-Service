package pl.itronics.home.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.itronics.home.domain.DatabaseConnectionInfo;
import pl.itronics.home.service.MaintenanceService;

@RestController
@Api(value = "maintenance", tags = {"maintenance"})
public class MaintenanceController {
    @Autowired
    private MaintenanceService maintenanceService;

    public MaintenanceController(MaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/maintenance/setDbCredentials")
    public ResponseEntity<String> setDbCredentials(DatabaseConnectionInfo info) {
        String status = maintenanceService.setDatabaseConnectionInfo(info);
        return new ResponseEntity<>(status, status.equals("SUCCESS") ? HttpStatus.ACCEPTED : HttpStatus.NOT_ACCEPTABLE );
    }
}
