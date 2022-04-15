/*

 */
package com.albertflex.blacksky.config;

import com.albertflex.blacksky.common.RResult;
import com.albertflex.blacksky.service.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.HttpStatusReturningServerLogoutSuccessHandler;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.util.Arrays;

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
                        .successHandler((req,res,auth)->{
                            res.setStatus(HttpStatus.OK.value());
                        })
                        .failureHandler(new SimpleUrlAuthenticationFailureHandler())
                .and()
                    .logout()
                    .logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                .and().exceptionHandling().authenticationEntryPoint(
                        new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and().cors()
                .configurationSource(corsConfigurationSource())
                .and()
                    .csrf().disable();
    }

    CorsConfigurationSource corsConfigurationSource(){
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration=new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setMaxAge(Duration.ofHours(2));
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

    //自定义权限验证错误入口
    private class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint{
        private final ObjectMapper objectMapper=new ObjectMapper();
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            RResult result=new RResult(999L,authException.getMessage());
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(401);
            response.getWriter().write(objectMapper.writeValueAsString(result));
            response.getWriter().write("{\"status\":\"error\"}");
            response.getWriter().flush();
            response.getWriter().close();
        }
    }

}
