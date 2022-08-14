package com.norab.security;

import com.norab.backstage.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import java.util.concurrent.TimeUnit;

import static com.norab.security.Permissions.SHOW_WRITE;
import static com.norab.security.Roles.HR;
import static com.norab.security.Roles.STAFF;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final ApplicationUserDetailsService applicationUserDetailsService;

    public SecurityConfiguration(PasswordEncoder passwordEncoder, ApplicationUserDetailsService applicationUserDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserDetailsService = applicationUserDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .and()
            .authorizeRequests()
            .antMatchers("/", "index","/login/*", "/css/*", "/js/*").permitAll()
            .antMatchers("/management/api/**").hasRole(HR.name())
            .antMatchers("/swagger-ui/**").hasAnyRole(STAFF.name(), HR.name())
            .antMatchers("/v3/**").hasAnyRole(STAFF.name(), HR.name())
            .antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
            .antMatchers(HttpMethod.POST, "/api/v1/**").hasAuthority(SHOW_WRITE.getPermission())
            .antMatchers(HttpMethod.PUT, "/api/v1/**").hasAuthority(SHOW_WRITE.getPermission())
            .antMatchers(HttpMethod.DELETE, "/api/v1/**").hasAuthority(SHOW_WRITE.getPermission())
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
            .defaultSuccessUrl("/box", true)
            //.and()
            //.rememberMe()
            //.tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
            //.key("somethingverysecured")
            .and()
            .logout()
                .logoutUrl("/logout")
            .logoutSuccessUrl("/login");

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserDetailsService);
        return provider;
    }
}
