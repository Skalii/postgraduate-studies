package skaliy.web.server.postgraduatestudies.configs


import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository

import skaliy.web.server.postgraduatestudies.configs.security.CsrfHeaderFilter
import skaliy.web.server.postgraduatestudies.entities.enums.UserRole
import skaliy.web.server.postgraduatestudies.repositories.UsersRepository


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
                .authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/api/contact-info/get/all**").hasRole(UserRole.ADMIN.value)
                .antMatchers("/api/scientific-links/get/all**").hasRole(UserRole.ADMIN.value)
                .antMatchers("/api/study-info/get/all**").hasRole(UserRole.ADMIN.value)
                .antMatchers("/api/users/get/one**").hasRole(UserRole.ADMIN.value)
                //todo user/get/me
                .antMatchers("/api/users/get/all**").hasRole(UserRole.ADMIN.value)
                .antMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
//                .loginPage("/login/index").permitAll()
//                .and()
//                .logout().permitAll()
                .and()
                .addFilterAfter(CsrfHeaderFilter(), CsrfFilter::class.java)
                .csrf().csrfTokenRepository(csrfTokenRepository())
    }

    private fun csrfTokenRepository(): CsrfTokenRepository {
        val repository = HttpSessionCsrfTokenRepository()
        repository.setHeaderName("X-XSRF-TOKEN")
        return repository
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Autowired
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        usersRepository.getAll(true)?.forEach { user ->
            println()
            println("ID: ${user.idUser}")
            println("Email: ${user.contactInfo.email}")
            println("Password: ${usersRepository.getPassword(user.idUser)}")
            println("Salt: ${user.salt}")
            println("Hash: ${user.hash}")
            println("Role: ${user.role.name} - ${user.role.value}")
            println()
            auth
                    .inMemoryAuthentication()
                    .withUser(user.contactInfo.email)
                    .password(passwordEncoder().encode(usersRepository.getPassword(user.idUser)))
                    .roles(user.role.value)
        }
    }

}