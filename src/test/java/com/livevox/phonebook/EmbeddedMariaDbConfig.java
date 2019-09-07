package com.livevox.phonebook;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,
    SecurityAutoConfiguration.class})
public class EmbeddedMariaDbConfig {

  @Bean
  public MariaDB4jSpringService mariaDB4jSpringService() {
    return new MariaDB4jSpringService();
  }

  @Bean
  DataSource dataSource(MariaDB4jSpringService mariaDB4jSpringService,
      DataSourceProperties dataSourceProperties) throws ManagedProcessException {

    //Create our database with default root user and no password
    final String dbName = dataSourceProperties.determineDatabaseName();
    mariaDB4jSpringService.getDB()
        .createDB(dbName);

    return DataSourceBuilder.create()
        .driverClassName(dataSourceProperties.determineDriverClassName())
        .username(dataSourceProperties.getUsername())
        .password(dataSourceProperties.getPassword())
        .url(dataSourceProperties.getUrl())
        .driverClassName(dataSourceProperties.determineDriverClassName())
        .build();
  }

}