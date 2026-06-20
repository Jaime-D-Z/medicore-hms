package com.hms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userDetailsService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/about", "/service", "/feature", "/team", "/testimonial",
                    "/contact", "/appointment", "/signin", "/createaccount", "/createuser",
                    "/css/**", "/js/**", "/lib/**", "/img/**", "/fonts/**", "/webjars/**").permitAll()
                .requestMatchers("/dashboard/**").authenticated()
                .requestMatchers("/dashboard/dRegistration/**", "/dashboard/doctorList",
                    "/dashboard/medicines/**", "/dashboard/stock/**", "/dashboard/suppliers/**",
                    "/dashboard/labtests/**", "/dashboard/testResults/**",
                    "/dashboard/departments/**", "/dashboard/staff/**", "/dashboard/insurances/**").hasRole("ADMIN")
                .requestMatchers("/dashboard/prsRegistration/**", "/dashboard/prescriptionList",
                    "/dashboard/medicalRecords/**", "/dashboard/history").hasAnyRole("ADMIN", "DOCTOR")
                .requestMatchers("/dashboard/patientbillRegistration/**", "/dashboard/billList",
                    "/dashboard/payments/**").hasAnyRole("ADMIN", "RECEPTIONIST")
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/signin")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/dashboard")
                .failureUrl("/signin?error=true")
                .permitAll()
            )
            .logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .permitAll()
            )
            .authenticationProvider(authenticationProvider());
        return http.build();
    }
}
