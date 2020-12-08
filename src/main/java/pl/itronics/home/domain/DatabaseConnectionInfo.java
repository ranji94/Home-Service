package pl.itronics.home.domain;

public class DatabaseConnectionInfo {
    private String user;
    private String password;
    private String server;
    private String database;

    public DatabaseConnectionInfo(String user, String password, String server, String database) {
        this.user = user;
        this.password = password;
        this.server = server;
        this.database = database;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }
}
