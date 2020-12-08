package pl.itronics.home.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import pl.itronics.home.utils.LocalCreds;

import javax.sql.DataSource;

@Configuration
@ComponentScan("pl.itronics.home")
public class SpringJDBCConfig {
    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://" +
                LocalCreds.getCredentials("db:server") +
                "/" +
                LocalCreds.getCredentials("db:database"));
        dataSource.setUsername(LocalCreds.getCredentials("db:user"));
        dataSource.setPassword(LocalCreds.getCredentials("db:pass"));

        return dataSource;
    }
}
