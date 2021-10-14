package com.example.store.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.store.service.MyUserDetailSevice;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailSevice userservice;

    @Autowired
    BCryptPasswordEncoder pa;

    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userservice);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests().antMatchers("/profile/**").authenticated().antMatchers("/order/**").authenticated().antMatchers("/admin/**")
                .hasAnyRole("STAF", "DIRE").antMatchers("/api/authorities").hasAnyRole("DIRE").anyRequest().permitAll();

        http.formLogin().loginPage("/security/login/form").loginProcessingUrl("/security/login")
                .defaultSuccessUrl("/secutity/login/success", true).failureUrl("/secutity/login/error");
        http.rememberMe().tokenValiditySeconds(86400);
        http.exceptionHandling().accessDeniedPage("/security/unauthoried");
        http.logout().logoutUrl("/security/logoff").logoutSuccessUrl("/security/logoff/success");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
    }
}
