//package skaliy.web.server.postgraduatestudies.configs


//import javax.sql.DataSource

//import org.springframework.boot.autoconfigure.domain.EntityScan
//import org.springframework.boot.context.properties.ConfigurationProperties
//import org.springframework.boot.jdbc.DataSourceBuilder
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.context.annotation.Primary
//import org.springframework.transaction.annotation.EnableTransactionManagement


/*
@Configuration
@EnableTransactionManagement
open class DataBaseConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    open fun getDataSource() = DataSourceBuilder
            .create()
            .url("jdbc:postgresql://localhost:5432/db") //todo set db
            .username("user") //todo set user
            .password("password") //todo set password
            .driverClassName("org.postgresql.Driver")
            .build()!!

}*/
