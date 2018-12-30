package com.developer.springOracle3.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService).passwordEncoder(encodePWD());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/images/jcaptcha", "/user/**",
                        "/soap/**", "/register/**", "/approve/**",
                        "/forget/**", "/cdn/**", "/cus1/**",
                        "/hystrix.stream", "/swagger-ui.html",
                        "/webjars/springfox-swagger-ui/**", "/configuration/ui",
                        "/swagger-resources", "/v2/api-docs",
                        "/swagger-resources/configuration/ui",
                        "/swagger-resources/configuration/security",
                        "/templates/doc/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/cp/**").permitAll()
                .antMatchers("/pr/productionPage").permitAll()
                .antMatchers("/pr/savePr").permitAll()
                .antMatchers("/pr/deletePr/{id}").permitAll()
                .antMatchers("/rp/resultTest").permitAll()
                .antMatchers("/cu/customerPage").permitAll()
                .antMatchers("/cu/saveCu").permitAll()
                .antMatchers("/cu/saveCu2").permitAll()
                .antMatchers("/cu//deleteCu/{id}").permitAll()
                .antMatchers("/").authenticated()
                .antMatchers("/cu/**", "/pr/**", "/rp/**").hasRole("admin").anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/logPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/404");

        http.sessionManagement().maximumSessions(1);
    }

    @Bean
    public BCryptPasswordEncoder encodePWD() {

        return new BCryptPasswordEncoder();
    }


}
