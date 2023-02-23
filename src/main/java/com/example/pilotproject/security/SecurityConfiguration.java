package com.example.pilotproject.security;

import com.example.pilotproject.security.jwt.JwtConfig;
import com.example.pilotproject.security.jwt.JwtVerifier;
import com.example.pilotproject.security.jwt.UsernameAndPasswordFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtConfig jwtConfig;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .addFilter(new UsernameAndPasswordFilter(authenticationManager(), jwtConfig))
                .addFilterAfter(new JwtVerifier(jwtConfig), UsernameAndPasswordFilter.class)
                .authorizeRequests()
                .antMatchers("/api/register/**").permitAll()
                .anyRequest()
                .authenticated();
    }

}
