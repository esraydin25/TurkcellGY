package kodlama.io.ecommerce.configuration.security;

import kodlama.io.ecommerce.business.concretes.CustomUserDetailsManager;
import kodlama.io.ecommerce.filter.CustomAuthenticationFilter;
import kodlama.io.ecommerce.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthenticationConfiguration configuration;
    private final CustomUserDetailsManager userDetailsManager;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter authenticationFilter
                = new CustomAuthenticationFilter(authenticationManager(configuration));
        authenticationFilter.setFilterProcessesUrl("/api/user/login");

        return http
                .csrf().disable()
                .cors().and()
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(HttpMethod.POST,"/api/users").permitAll();
                    auth.requestMatchers("/api/user/login").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/api/products/**").permitAll();
                    auth.requestMatchers(HttpMethod.GET,"/api/categories/**").permitAll();
                    auth.requestMatchers(HttpMethod.POST, "/api/products/**").hasAuthority("ADMIN");
                    auth.requestMatchers(HttpMethod.DELETE, "/api/products/**").hasAuthority("ADMIN");
                    auth.anyRequest().authenticated();
                })
                .formLogin().disable()
                .httpBasic().disable()
                .addFilter(authenticationFilter)
                .addFilterBefore(new CustomAuthorizationFilter(userDetailsManager), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
