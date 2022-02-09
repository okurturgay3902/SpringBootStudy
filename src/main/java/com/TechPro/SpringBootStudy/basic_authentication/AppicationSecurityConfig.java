package com.TechPro.SpringBootStudy.basic_authentication;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration  // Class'i config olarak tanimlar class name'in config kismi
@EnableWebSecurity
// tanimli oldugu class'da form based security yerine(basic authentication) configure eder
public class AppicationSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.
               authorizeRequests().//Requstler icin yetki sorgula (get put patch delete post)
               antMatchers("/","index", "/css/*", "/js/*").permitAll().
                                            // antMatchers method parametresimdeki url'lere izin ver sifre isteme
               anyRequest(). // her request icin

               authenticated(). // kullanici sorgula
               and(). // neye gore
               httpBasic(); //httpBasic'e gore
        // her request'te APP username ve passv.(security) control edilmeli logout yapmaya gerek kalmayacak
        // Benim istedigim kisiler apt girdikten sonra herkes yetki sahibi olsun
    }

    @Override
    protected UserDetailsService userDetailsService() {

      UserDetails student= User.builder().username("turgay").password("1111").roles("sefil AGA").build();
      UserDetails prens= User.builder().username("melih").password("2222").roles("sefil KAHYA").build();
      UserDetails admin= User.builder().username("sueda").password("3333").roles("ADMIN").build(); // convension tanim

       // return student; //return type uyumsuzluk hatasi
        return new InMemoryUserDetailsManager(student,prens,admin);

    }
}
