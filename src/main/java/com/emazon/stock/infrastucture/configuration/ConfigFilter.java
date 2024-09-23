package com.emazon.stock.infrastucture.configuration;

import com.emazon.stock.infrastucture.output.security.jwt.JwtAuthenticationFilter;
import com.emazon.stock.utils.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigFilter {
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers("/auth/**").permitAll()
                                .requestMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                                 //Article
                                .requestMatchers(HttpMethod.POST,"/article/price").hasAuthority(SecurityConstants.ROLE_CLIENT)
                                .requestMatchers(HttpMethod.POST,"/article/**").hasAuthority(SecurityConstants.ROLE_ADMIN)
                                .requestMatchers(HttpMethod.PATCH,"/article/updateQuantity").hasAuthority(SecurityConstants.ROLE_WAREHOUSE_ASSISTANT)
                                .requestMatchers(HttpMethod.GET, "/article/**").permitAll()
                                 //Category
                                .requestMatchers(HttpMethod.POST,"/category/quantities").hasAuthority(SecurityConstants.ROLE_CLIENT)
                                .requestMatchers(HttpMethod.POST,"/category/**").hasAuthority(SecurityConstants.ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET,"/category/**").permitAll()
                                //Brand
                                .requestMatchers(HttpMethod.POST,"/brand/**").hasAuthority(SecurityConstants.ROLE_ADMIN)
                                .requestMatchers(HttpMethod.GET,"/brand/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}