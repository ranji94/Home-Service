package pl.itronics.home.service;

import pl.itronics.home.domain.DatabaseConnectionInfo;

public interface MaintenanceService {
    public String setDatabaseConnectionInfo(DatabaseConnectionInfo dbConnection);
}
