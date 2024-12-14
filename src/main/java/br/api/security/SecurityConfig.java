package br.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(org.springframework.security.core.userdetails.User
                .withUsername("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build());
        manager.createUser(org.springframework.security.core.userdetails.User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build());
        return manager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                    .antMatchers("/login", "/public/**").permitAll()  // Allow unauthenticated access to login and public pages
                    .antMatchers("/admin/**").hasRole("ADMIN")       // Restrict access to /admin/** to ADMIN role only
                    .antMatchers("/user/**").hasAnyRole("USER", "ADMIN")  // Allow access to /user/** for USER and ADMIN roles
                    .anyRequest().authenticated()                        // All other requests require authentication
            )
            .formLogin(usuario -> usuario
                    .loginPage("/login")                                // Custom login page
                    .defaultSuccessUrl("/api/aluno/insert", true)                   // Redirect to home after successful login
                    .failureUrl("/login?error=true")                    // Redirect to login page after failed login
            )
            //preciso que o código redirecione para outra página antes do logout
            .logout(logout -> logout
                    .logoutSuccessUrl("/login?logout=true")             // Redirect to login page after logout
            );
    }
}