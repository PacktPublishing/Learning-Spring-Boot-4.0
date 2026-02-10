package com.learningspringboot4;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  CommandLineRunner initUsers(UserManagementRepository repository) {
    return args -> {
      repository.save(new UserAccount("user", "password", "ROLE_USER"));
      repository.save(new UserAccount("admin", "password", "ROLE_ADMIN"));
    };
  }

  @Bean
  UserDetailsService userService(UserRepository repo) {
    return username -> repo.findByUsername(username).asUser();
  }
  
  @Bean
  SecurityFilterChain configureSecurity(HttpSecurity http) throws Exception {
    http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/", "/search").authenticated()
                    .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "/new-video", "/api/**").hasRole("ADMIN")
                    .anyRequest().denyAll()
            )
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults());

    return http.build();
  }

}
