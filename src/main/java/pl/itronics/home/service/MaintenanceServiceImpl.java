package pl.itronics.home.service;

import org.springframework.stereotype.Service;
import pl.itronics.home.domain.DatabaseConnectionInfo;
import pl.itronics.home.utils.FileOperations;

import java.io.IOException;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {
    @Override
    public String setDatabaseConnectionInfo(DatabaseConnectionInfo dbConnection) {
        try {
            FileOperations.writeDbCredentials(dbConnection);
            return "SUCCESS";
        }
        catch(IOException e) {
            return "FAILED";
        }
    }
}
