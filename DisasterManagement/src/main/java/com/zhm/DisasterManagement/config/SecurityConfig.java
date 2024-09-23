package com.zhm.DisasterManagement.config;

import com.zhm.DisasterManagement.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    private CustomUserDetailsService customUserDetailsService;
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private String[] publicUrl = {
            "/",
            "/home",
            "/donation/**",
            "/crisis/**",
            "/register/**",
            "/css/**",
            "/images/**",
            "/crisis-images/**",
            "/volunteer-images/**",
            "/volunteers/**",
            "/register/**",
            "/resources/**",
            "/assets/**",
            "/js/**",
            "/*.css",
            "/*.js",
            "/*.js.map",
            "/fonts**",
            "/favicon.ico",
            "/error"
    };

    @Autowired
    public SecurityConfig(CustomUserDetailsService customUserDetailsService, CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler) {
        this.customUserDetailsService = customUserDetailsService;
        this.customAuthenticationSuccessHandler = customAuthenticationSuccessHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers(publicUrl).permitAll()
                .requestMatchers("/admin/**").hasAuthority("Admin")
                .requestMatchers("/volunteer/**").hasAuthority("Volunteer")
                .requestMatchers("/inventory/**").hasAnyAuthority("Admin", "Volunteer")
                .anyRequest().authenticated()
        )
        .exceptionHandling(configurer -> configurer
                .accessDeniedPage("/access-denied")
        )
        .formLogin(user -> user
                .loginPage("/login")
                .permitAll()
                .successHandler(customAuthenticationSuccessHandler)
        )
        .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
        );

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(this.customUserDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
