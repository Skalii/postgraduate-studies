/*
package skaliy.web.server.postgraduatestudies.configs.initializers


import javax.servlet.ServletContext

import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext.DISPATCHER_SERVLET_NAME
import org.springframework.web.WebApplicationInitializer
import org.springframework.web.context.ContextLoaderListener
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext
import org.springframework.web.servlet.DispatcherServlet

import skaliy.web.server.postgraduatestudies.configs.SecurityConfig
import skaliy.web.server.postgraduatestudies.configs.WebConfig


class Initializer : WebApplicationInitializer {

    override fun onStartup(servletContext: ServletContext) {
        val ctx = AnnotationConfigWebApplicationContext()
        ctx.register(WebConfig::class.java)
        ctx.register(SecurityConfig::class.java)

        servletContext.addListener(ContextLoaderListener(ctx))

        ctx.servletContext = servletContext

        val servlet = servletContext.addServlet(DISPATCHER_SERVLET_NAME, DispatcherServlet(ctx))
        servlet.addMapping("/")
        servlet.setLoadOnStartup(1)
    }

}*/
