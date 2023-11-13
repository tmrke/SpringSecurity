package ru.ageev.SpringSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.ageev.SpringSecurity.service.PersonDetailsService;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final PersonDetailsService personDetailsService;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        (auth) -> auth.requestMatchers("/auth/login", "/error", "auth/registration", "/auth/login_error").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .anyRequest().hasAnyRole("GUEST", "USER", "ADMIN")
                )
                .formLogin(
                        (form) -> form.
                                loginPage("/auth/login")
                                .loginProcessingUrl("/process_login")
                                .defaultSuccessUrl("/person", true)
                                .failureUrl("/auth/login?error")
                )
                .logout((logout) -> logout.logoutUrl("/logout").logoutSuccessUrl("/auth/login"));

        return http.build();
    }


    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
