package ua.zakharenko.LibraryManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ua.zakharenko.LibraryManagementSystem.entity.Person;
import ua.zakharenko.LibraryManagementSystem.security.CustomAuthenticationSuccessHandler;
import ua.zakharenko.LibraryManagementSystem.security.PersonDetails;
import ua.zakharenko.LibraryManagementSystem.service.impl.PersonDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final PersonDetailsService personDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(PersonDetailsService personDetailsService, PasswordEncoder passwordEncoder) {
        this.personDetailsService = personDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //Здесь конфигурируем Spring Security
        //Здесь конфигурируем авторизацию

        http.authorizeHttpRequests((authz) -> authz.requestMatchers("/", "/categories",
                                "/addCategory", "/books", "/add", "/publishers", "/addPublisher",
                                "/authors", "/addAuthor", "/admin").hasRole("ADMIN")
                        .requestMatchers("/auth/login", "/auth/registration" ,"/error",
                                "/css/style.css", "/css/search.css").permitAll().requestMatchers(HttpMethod.POST, "/save").permitAll()
                        .anyRequest().hasAnyRole("USER", "ADMIN"))
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .successHandler(new CustomAuthenticationSuccessHandler())//название того адреса куда мы передаем данные с формы
                .failureUrl("/auth/login?error")
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/auth/login")         //логаут это когда сессия пользователя удаляется и его кукисы
                .and()
                .exceptionHandling()
                .accessDeniedHandler((req, resp, e) -> {
                    // Handle access denied exception by redirecting to custom 403 page
                    resp.sendRedirect("/403");
                })
        ;
        return http.build();
    }
}
