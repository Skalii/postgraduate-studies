package skaliy.web.server.postgraduatestudies.configs

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.*
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
//@EnableTransactionManagement
open class DataBaseConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    open fun getDataSource() = DataSourceBuilder
            .create()
            .url("jdbc:postgresql://localhost:5432/postgraduate_studies")
            .username("postgres")
            .password("pass129049p")
            .driverClassName("org.postgresql.Driver")
            .build()!!
}