package skaliy.web.server.postgraduatestudies.security


import org.springframework.security.web.csrf.CsrfToken
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.WebUtils

import java.io.IOException

import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class CsrfHeaderFilter : OncePerRequestFilter() {

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
            request: HttpServletRequest,
            response: HttpServletResponse,
            filterChain: FilterChain
    ) {
        val csrf = request.getAttribute(CsrfToken::class.java
                .name) as CsrfToken

        var cookie: Cookie? = WebUtils.getCookie(request, "XSRF-TOKEN")

        val token = csrf.token

        if (cookie == null || token != null && token != cookie.value) {
            cookie = Cookie("XSRF-TOKEN", token)
            cookie.path = "/"
            response.addCookie(cookie)
        }

        filterChain.doFilter(request, response)
    }

}