package skaliy.web.server.postgraduatestudies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@PropertySource(value = ["classpath:application.properties"])
@ComponentScan(value = ["skaliy.web.server.postgraduatestudies"])
@EntityScan(basePackages = ["skaliy.web.server.postgraduatestudies.entities"])
@EnableJpaRepositories(value = ["skaliy.web.server.postgraduatestudies.repositories"])
class PostgraduateStudiesApplication

fun main(args: Array<String>) {
    runApplication<PostgraduateStudiesApplication>(*args)
}
