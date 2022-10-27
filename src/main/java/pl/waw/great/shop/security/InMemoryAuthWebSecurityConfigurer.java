package pl.waw.great.shop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class InMemoryAuthWebSecurityConfigurer {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        UserDetails user = User.withUsername("spring")
                .password(encoder.encode("secret"))
                .roles("USER")
                .build();
        UserDetails user1 = User.withUsername("mikolaj")
                .password(encoder.encode("mikolaj"))
                .roles("USER")
                .build();

        UserDetails user2 = User.withUsername("lukasz")
                .password(encoder.encode("lukasz"))
                .roles("USER")
                .build();

        UserDetails user3 = User.withUsername("boro")
                .password(encoder.encode("boro"))
                .roles("USER")
                .build();

        UserDetails user4 = User.withUsername("bartek")
                .password(encoder.encode("bartek"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, user1, user2, user3, user4);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/user/create")
                .permitAll()
                .antMatchers("/product/allProducts")
                .permitAll()
                .antMatchers("/product/**")
                .authenticated()
                .antMatchers("/order")
                .authenticated()
                .and()
                .csrf().disable()
                .httpBasic();
        return http.build();
    }
}