/*

 */
package com.albertflex.blacksky.config;

import com.albertflex.blacksky.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Administrator
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserServices userServices;

    @Autowired
    public SecurityConfig(UserServices userServices) {
        this.userServices = userServices;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServices)
                .passwordEncoder(passwordEncoder());
    }

    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/artifact/**", "/post/**", "/user/**")
                    .hasAuthority("ROLE_USER")
                .antMatchers("/admin/**")
                    .hasAuthority("ROLE_ADMIN")
                .antMatchers("/file_resource/**")
                    .permitAll()
                .antMatchers("/**")
                    .permitAll()
                .and()
                    .formLogin()
                .and()
                    .csrf().disable();
    }
}
