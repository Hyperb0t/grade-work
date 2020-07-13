package ru.itis.jaboderzhateli.gradework.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Configuration
    @Order(0)
    @AllArgsConstructor
    public static class GeneralCOnfig extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;
        private final PasswordEncoder passwordEncoder;
        private final DataSource dataSource;

        @Bean
        public PersistentTokenRepository persistentTokenRepository() {
            var repository = new JdbcTokenRepositoryImpl();
            repository.setDataSource(dataSource);

            return repository;
        }

        @Override
        public void configure(WebSecurity web) {
            web.ignoring().antMatchers("/static/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests();

            http.formLogin()
                    .loginPage("/sign_In")
                    .usernameParameter("login")
                    .passwordParameter("password")
                    .permitAll();

            http.logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/signOut", "GET"))
                    .permitAll();

            http.rememberMe()
                    .alwaysRemember(true)
                    .tokenRepository(persistentTokenRepository());
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        }

    }

}
