/*package skaliy.web.server.postgraduatestudies.configs


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import org.springframework.web.servlet.view.JstlView
import org.springframework.web.servlet.view.UrlBasedViewResolver


@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurerAdapter() {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/", "classpath:templates/")
    }

    *//*@Bean
    fun setupViewResolver() = UrlBasedViewResolver()
            .apply { setViewClass(JstlView::class.java) }
            .apply { setPrefix("classpath:templates/") }
            .apply { setSuffix(".html") }
            .apply { setContentType("text/html;charset=UTF-8") }*//*

}*/

/*import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
    }
}*/

/*
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
//import org.springframework.web.servlet.config.annotation.ViewResolverRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
//import org.springframework.web.servlet.view.json.MappingJackson2JsonView
import org.thymeleaf.spring5.SpringTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.ThymeleafViewResolver
import org.thymeleaf.templatemode.TemplateMode


@EnableWebMvc
@Configuration
open class WebConfig :
        WebMvcConfigurerAdapter(),
        ApplicationContextAware {


    private lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    override fun addViewControllers(registry: ViewControllerRegistry) {
        super.addViewControllers(registry)

//        registry!!.addViewController("/welcome.html")
//        registry!!.addViewController("/home.html")
    }


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
            .apply { setApplicationContext(applicationContext) }

    @Bean
    open fun templateEngine() = SpringTemplateEngine()
            .apply { setTemplateResolver(templateResolver()) }

    @Bean
    open fun viewResolver() = ThymeleafViewResolver()
            .apply { characterEncoding = "UTF-8" }
            .apply { contentType = "text/html;charset=UTF-8" }
            .apply { forceContentType = true }
            .apply { templateEngine = templateEngine() }
            .apply { order = 1 }


}
*/