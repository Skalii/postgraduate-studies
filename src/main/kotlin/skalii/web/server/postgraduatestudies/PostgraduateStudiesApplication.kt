package skalii.web.server.postgraduatestudies


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan(basePackages = ["skalii.web.server.postgraduatestudies"])
class PostgraduateStudiesApplication

fun main(args: Array<String>) {
    runApplication<PostgraduateStudiesApplication>(*args)
}
