package test.config;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

    private final DataSource dataSource;
    public LiquibaseConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }
}
