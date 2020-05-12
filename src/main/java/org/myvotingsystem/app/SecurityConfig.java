package org.myvotingsystem.app;

import org.myvotingsystem.app.util.PasswordUtil;
import org.myvotingsystem.app.web.CustomAccessDeniedHandler;
import org.myvotingsystem.app.web.CustomAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "userService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return PasswordUtil.getPasswordEncoder();
    }
    @Bean
    public CustomAccessDeniedHandler getAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }
    @Bean
    public CustomAuthenticationEntryPoint getAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .antMatcher("/rest/**")
                .authorizeRequests()
                .antMatchers("/rest/admin/**").hasRole("ADMIN")
                .antMatchers("/rest/profile").authenticated()
                .antMatchers("/rest/restaurants").authenticated()
                .antMatchers("/rest/menus").authenticated()
                .and().httpBasic()
                .authenticationEntryPoint(getAuthenticationEntryPoint())
                .and().exceptionHandling()
                .accessDeniedHandler(getAccessDeniedHandler())
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
