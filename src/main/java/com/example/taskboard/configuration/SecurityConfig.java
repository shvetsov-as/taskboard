package com.example.taskboard.configuration;


import com.example.taskboard.model.security.passwordManager.HashSaltCollector;
import com.example.taskboard.model.security.passwordManager.PasswordEncoderService;
import com.example.taskboard.model.security.passwordManager.PasswordMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()//cross site request forgery
                .authorizeRequests()
                .antMatchers("/api/v1/users").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(); ///api/v1/users/{id}
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Bean
    protected PasswordEncoder passwordEncoder(HashSaltCollector hashSaltCollector, PasswordMatcher passwordMatcher){
        return new PasswordEncoderService(hashSaltCollector, passwordMatcher);
    }

    @Bean
    protected DaoAuthenticationProvider daoAuthenticationProvider(PasswordEncoder passwordEncoder){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        return daoAuthenticationProvider;
    }
}
