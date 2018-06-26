/*
package skalii.web.server.postgraduatestudies.security


import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

import java.io.IOException

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.FilterConfig
import javax.servlet.ServletException
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
internal class CorsFilter : Filter {

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(
            req: ServletRequest,
            res: ServletResponse,
            chain: FilterChain
    ) {
        val response = res as HttpServletResponse
        val request = req as HttpServletRequest
        response.setHeader("Access-Control-Allow-Origin", "*") // FIXME this has to get fixed up to make secure in the future
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE")
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with")
        response.setHeader("Access-Control-Max-Age", "3600")
        if ("OPTIONS" != request.method) {
            chain.doFilter(req, res)
        } else {
        }
    }

    override fun init(filterConfig: FilterConfig) {}

    override fun destroy() {}

}*/
