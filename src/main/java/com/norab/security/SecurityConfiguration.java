package com.norab.security;

import com.norab.backstage.auth.ApplicationUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.norab.security.Roles.HR;

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
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
            .antMatchers("/management/api/**").hasRole(HR.name())
            .anyRequest()
            .authenticated()
            .and()
            .formLogin();

        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserDetailsService);
        return provider;
    }

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
            .username("user")
            .password(passwordEncoder.encode("1234"))
            .roles(Roles.USER.name())
            .build();

        UserDetails staff = User.builder()
            .username("staff")
            .password(passwordEncoder.encode("1234"))
            .roles(Roles.STAFF.name())
            .build();

        UserDetails hr = User.builder()
            .username("hr")
            .password(passwordEncoder.encode("1234"))
            .roles(HR.name())
            .build();

        return new InMemoryUserDetailsManager(
            user,
            staff,
            hr
        );
    }
     */
}
