package com.htp.security;

import com.htp.security.filter.AuthenticationTokenFilter;
import com.htp.security.util.TokenUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final TokenUtils tokenUtils;

    public WebSecurityConfiguration(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService,
                                    TokenUtils tokenUtils) {
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean(AuthenticationManager authenticationManager){
        AuthenticationTokenFilter authenticationTokenFilter = new AuthenticationTokenFilter(tokenUtils, userDetailsService);
        authenticationTokenFilter.setAuthenticationManager(authenticationManager);
        return authenticationTokenFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring()
                .antMatchers("/v2/api-docs/**", "/configuration/ui/**", "/swagger-resources/**",
                        "/configuration/security/**", "/swagger-ui.html", "/webjars/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling().and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/swagger-ui.html#").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/registration/**").permitAll()
                .antMatchers("/logout/**").permitAll()
                .antMatchers("/statistic/**").permitAll()
                .antMatchers("/hello/**").permitAll()

                .antMatchers(HttpMethod.GET,"/users/**").permitAll()
                .antMatchers(HttpMethod.POST,"/users/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.PUT,"/users/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.DELETE,"/users/**").hasAnyRole("ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/roles/**").permitAll()
                .antMatchers(HttpMethod.POST,"/roles/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"roles/**").hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/roles/**").hasAnyRole("ADMIN")

                .antMatchers(HttpMethod.GET,"/buildings/**").permitAll()
                .antMatchers(HttpMethod.POST,"/buildings/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.PUT,"/buildings/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.DELETE,"/buildings/**").hasAnyRole("ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/activities/**").permitAll()
                .antMatchers(HttpMethod.POST,"/activities/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.PUT,"/activities/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.DELETE,"/activities/**").hasAnyRole("ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/activities_request/**").permitAll()
                .antMatchers(HttpMethod.POST,"/activities_request/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.PUT,"/activities_request/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.DELETE,"/activities_request/**").hasAnyRole("ADMIN", "MODERATOR")

                .antMatchers(HttpMethod.GET,"/chat/**").permitAll()
                .antMatchers(HttpMethod.POST,"/chat/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.PUT,"/chat/**").hasAnyRole("USER", "ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.DELETE,"/chat/**").hasAnyRole("ADMIN", "MODERATOR")

                .antMatchers("/delete/**").hasAnyRole("ADMIN", "MODERATOR")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .anyRequest().authenticated();
        http.addFilterBefore(authenticationTokenFilterBean(authenticationManagerBean()),
                UsernamePasswordAuthenticationFilter.class);
    }
}
