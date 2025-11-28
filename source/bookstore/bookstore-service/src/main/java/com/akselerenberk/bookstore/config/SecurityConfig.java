package com.akselerenberk.bookstore.config;

import com.akselerenberk.bookstore.core.models.Role;
import com.akselerenberk.bookstore.security.AccessDeniedHandlerImpl;
import com.akselerenberk.bookstore.security.AuthTokenFilter;
import com.akselerenberk.bookstore.security.HandlerAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final AuthTokenFilter authTokenFilter;

    /**
     * Used for storing a password that needs to be compared to the user-provided password at the time of authentication.
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines how Spring Security Filters perform authentication.
     *
     * @param authConfig the AuthenticationConfiguration
     * @return AuthenticationManager
     * @throws Exception an exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * The AuthenticationProviders will handle each authentication request and, in the event of successful authentication, return an Authentication object. Otherwise, the provider will throw an exception.
     *
     * @return AuthenticationProvider
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    /**
     * Defines all filters chain which is capable of being matched against an HttpServletRequest.
     *
     * @param http the HttpSecurity
     * @return SecurityFilterChain
     * @throws Exception an exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .csrf()
                .disable()

                // Add Paseto token filter
                .addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider())

                // Set unauthorized requests exception handler
                .exceptionHandling()
                .authenticationEntryPoint(new HandlerAuthenticationEntryPoint())
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                // Set permissions on endpoints
                .authorizeHttpRequests()
                .requestMatchers("/api/hello-world").permitAll()
                .requestMatchers("/api/account/authenticate").permitAll()
                .requestMatchers("/api/account/register").permitAll()
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/webjars/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                .requestMatchers("/api/admin/**").hasRole(Role.ADMIN.name());
        // @formatter:on
        return http.build();
    }

}