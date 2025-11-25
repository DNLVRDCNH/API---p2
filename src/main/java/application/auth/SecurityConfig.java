
package application.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println(">>> A CONFIGURAÇÃO DE SEGURANÇA FOI CARREGADA! <<<");
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                .requestMatchers(HttpMethod.GET, "/public").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/docs*/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/alunos").authenticated()
                .requestMatchers(HttpMethod.POST, "/cursos").authenticated()
                .requestMatchers(HttpMethod.POST, "/registros").authenticated()
                .requestMatchers(HttpMethod.PUT, "/alunos/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/cursos/**").authenticated()
                .requestMatchers(HttpMethod.PUT, "/registros/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/alunos/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/cursos/**").authenticated()
                .requestMatchers(HttpMethod.GET, "/registros/**").authenticated()
                .anyRequest().authenticated())
            .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
        AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
