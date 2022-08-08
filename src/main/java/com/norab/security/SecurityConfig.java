package com.norab.security;

/*import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;*/

/*@Configuration
@EnableWebSecurity*/
public class SecurityConfig {
    /*private final PasswordEncoder passwordEncoder;
    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            //.csrf().disable()
            .authorizeRequests()
            .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin();

        return http.build();
    }
*/

//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/api/v*/registration/**")
//            .permitAll()
//            .anyRequest()
//            .authenticated().and()
//            .formLogin();
//    }

    /*@Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
        return (web) -> web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
    }*/

    /*@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/



    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }*/

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/login").permitAll()
            .antMatchers("/users/**", "/settings/**").hasAuthority("Admin")
            .hasAnyAuthority("Admin", "Editor", "Salesperson")
            .hasAnyAuthority("Admin", "Editor", "Salesperson", "Shipper")
            .anyRequest().authenticated()
            .and().formLogin()
            .loginPage("/login")
            .usernameParameter("email")
            .permitAll()
            .and()
            .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
            .and()
            .logout().permitAll();

        http.headers().frameOptions().sameOrigin();

        return http.build();
    }*/
}
