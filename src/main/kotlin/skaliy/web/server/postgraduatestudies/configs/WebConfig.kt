package skaliy.web.server.postgraduatestudies.configs

//import org.springframework.context.ApplicationContext
//import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
//import org.springframework.http.MediaType
//import org.springframework.web.servlet.View
//import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer
import org.springframework.web.servlet.config.annotation.EnableWebMvc
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
//import org.springframework.web.servlet.view.json.MappingJackson2JsonView


@EnableWebMvc
@ComponentScan(value = ["skaliy.web.server.postgraduatestudies"])
@Configuration
open class WebConfig :
        WebMvcConfigurerAdapter()/*,
        ApplicationContextAware*/ {

    /*private lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }*/

    /*override fun addViewControllers(registry: ViewControllerRegistry) {
        super.addViewControllers(registry)

//        registry!!.addViewController("/welcome.html")
//        registry!!.addViewController("/home.html")
    }*/

    /*override fun configureViewResolvers(registry: ViewResolverRegistry) {
        registry.enableContentNegotiation(
                MappingJackson2JsonView())
    }*/

    /*@Bean("currencyRateView")
    fun jsonView(): View {
        return MappingJackson2JsonView()
    }*/

    /*override fun configureContentNegotiation(configurer: ContentNegotiationConfigurer) {
        configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8)
    }*/

    /*@Bean
    open fun templateResolver() = SpringResourceTemplateResolver()
            .apply { characterEncoding = "UTF-8" }
            .apply { prefix = "classpath:templates/" }
            .apply { suffix = ".html" }
            .apply { templateMode = TemplateMode.HTML5 }
            .apply { setApplicationContext(applicationContext) }*/

    /*@Bean
    open fun templateEngine() = SpringTemplateEngine()
            .apply { setTemplateResolver(templateResolver()) }*/

    /*@Bean
    open fun viewResolver() = ThymeleafViewResolver()
            .apply { characterEncoding = "UTF-8" }
            .apply { contentType = "text/html;charset=UTF-8" }
            .apply { forceContentType = true }
            .apply { templateEngine = templateEngine() }
            .apply { order = 1 }*/

}