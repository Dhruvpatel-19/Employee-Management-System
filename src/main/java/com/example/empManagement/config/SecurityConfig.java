package com.example.empManagement.config;

import com.example.empManagement.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

   @Override
    protected void configure(HttpSecurity http) throws Exception {
        //Basic Authentication
        //Form Based Authentication
       http
                .csrf().disable();
        http.httpBasic().and()
                .authorizeRequests()
                //.antMatchers("/home", "/login" , "/register").permitAll()
                //.antMatchers("/public/**").permitAll()
                //.antMatchers("/public/**").hasRole("NORMAL")
                //.antMatchers("/users/**").hasRole("ADMIN")
                .antMatchers("/addEmp").permitAll()
                .antMatchers("/delEmp/**").hasRole("ADMIN")
                .antMatchers("/updateEmp/**").hasRole("ADMIN")
                .antMatchers("/getEmp/**").hasAnyRole("ADMIN" , "NORMAL")
                .antMatchers("/getAllEmp").hasRole("ADMIN")
                .anyRequest().permitAll().and()
                //.httpBasic();
                .formLogin();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("AAA").password(this.passwordEncoder().encode("111")).roles("NORMAL");
        //auth.inMemoryAuthentication().withUser("BBB").password(this.passwordEncoder().encode("111")).roles("ADMIN");
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder() ;
    }
}
