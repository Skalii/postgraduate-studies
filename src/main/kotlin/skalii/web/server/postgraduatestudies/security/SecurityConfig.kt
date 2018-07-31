package skalii.web.server.postgraduatestudies.security


import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.security.web.csrf.CsrfFilter
//import org.springframework.security.web.csrf.CsrfTokenRepository
//import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository

import skalii.web.server.postgraduatestudies.entities.enums.UserRole.*
import skalii.web.server.postgraduatestudies.repositories.UsersRepository


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    lateinit var usersRepository: UsersRepository

    override fun configure(http: HttpSecurity?) {
        http!!
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        GET,
                        "/api/contact-info/one**",
                        "/api/contact-info/all**",
                        "/api/scientific-links/one**",
                        "/api/scientific-links/all**",
                        "/api/sections/one**",
                        "/api/sections/all**",
                        "/api/study-info/one**",
                        "/api/study-info/all**",
                        "/api/tasks/one**",
                        "/api/tasks/all**",
                        "/api/users/one**",
                        "/api/users/all**"
                ).hasRole(ADMIN.value)
                .antMatchers(
                        POST,
                        "/api/branches/**",
                        "/api/contact-info/**",
                        "/api/degrees/**",
                        "/api/departments/**",
                        "/api/faculties/**",
                        "/api/institutes/**",
                        "/api/scientific-links/**",
                        "/api/sections/one**",
                        "/api/specialities/**",
                        "/api/study-info/**",
                        "/api/users/**"
                ).hasRole(ADMIN.value)
                .antMatchers(
                        PUT,
                        "/api/branches/**",
                        "/api/contact-info/one**",
                        "/api/degrees/**",
                        "/api/departments/**",
                        "/api/faculties/**",
                        "/api/institutes/**",
                        "/api/scientific-links/one**",
                        "/api/sections/one**",
                        "/api/specialities/**",
                        "/api/study-info/one**",
                        "/api/tasks/one**",
                        "/api/users/one**"
                ).hasRole(ADMIN.value)
                .antMatchers(
                        DELETE,
                        "/api/branches/**",
                        "/api/contact-info/**",
                        "/api/degrees/**",
                        "/api/departments/**",
                        "/api/faculties/**",
                        "/api/institutes/**",
                        "/api/scientific-links/**",
                        "/api/sections/one**",
                        "/api/sections/all**",
                        "/api/specialities/**",
                        "/api/study-info/**",
                        "/api/tasks/one**",
                        "/api/tasks/all**",
                        "/api/users/**"
                ).hasRole(ADMIN.value)
                .antMatchers(
                        GET,
                        "/api/sections/my**",
                        "/api/study-info/my**",
                        "/api/tasks/my**",
                        "/api/users/my-instructor**"
                ).hasAnyRole(
                        GRADUATE_STUDENT.value,
                        DOCTORAL_STUDENT.value
                )
                .antMatchers(
                        POST,
                        "/api/sections/my**",
                        "/api/tasks/my**"
                ).hasAnyRole(
                        GRADUATE_STUDENT.value,
                        DOCTORAL_STUDENT.value
                )
                .antMatchers(
                        PUT,
                        "/api/sections/my**",
                        "/api/study-info/my**",
                        "/api/tasks/my-mark-student**"
                ).hasAnyRole(
                        GRADUATE_STUDENT.value,
                        DOCTORAL_STUDENT.value
                )
                .antMatchers(
                        DELETE,
                        "/api/sections/my**",
                        "/api/tasks/my**"
                ).hasAnyRole(
                        GRADUATE_STUDENT.value,
                        DOCTORAL_STUDENT.value
                )
                .antMatchers(
                        GET,
                        "/api/users/my-students**"
                ).hasRole(INSTRUCTOR.value)
                .antMatchers(
                        PUT,
                        "/api/tasks/my-mark-instructor**"
                ).hasRole(INSTRUCTOR.value)
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .logout().permitAll()
//                .and()
//                .addFilterAfter(CsrfHeaderFilter(), CsrfFilter::class.java)
//                .csrf().csrfTokenRepository(csrfTokenRepository())
    }

//    private fun csrfTokenRepository(): CsrfTokenRepository {
//        val repository = HttpSessionCsrfTokenRepository()
//        repository.setHeaderName("X-XSRF-TOKEN")
//        return repository
//    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        usersRepository.getAll(true).forEach { user ->
            auth
                    .inMemoryAuthentication()
                    .withUser(user.contactInfo.email)
                    .password(passwordEncoder().encode(usersRepository.decrypt(user.idUser)))
                    .roles(user.role.value)
        }
    }

}