//package com.isoft.code.stackoverflowclone.commons.security;
//
//import com.isoft.code.stackoverflowclone.commons.auth.AuthEntryPointJwt;
//import com.isoft.code.stackoverflowclone.commons.auth.JwtTokenFilter;
//import com.isoft.code.stackoverflowclone.commons.auth.JwtTokenProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//import javax.servlet.Filter;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig implements WebSecurityCustomizer, SecurityFilterChain {
//
//    private static final String[] AUTH_LIST = {
//        "**/swagger-resources/**",
//        "/swagger-resources/**",
//        "/swagger-ui.html/**",
//        "/webjars/**",
//        "/v2/api-docs",
//        "/health**",
//        "/info**",
//    };
//
//    @Autowired
//    private JwtTokenProvider jwtTokenProvider;
//
//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler;
//
//    @Bean
//    public JwtTokenFilter authenticationJwtTokenFilter() {
//        return new JwtTokenFilter(jwtTokenProvider);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
//        http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
//
//        http.csrf().disable().cors().and().authorizeRequests()
//                .mvcMatchers(HttpMethod.POST, "/api/users").permitAll()
//                .antMatchers("/api/users/login").permitAll()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated();
//        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//
//        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
//        corsConfiguration.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", corsConfiguration);
//
//        return source;
//    }
//
//    @Override
//    public void customize(WebSecurity web) {
//        web.ignoring().antMatchers(AUTH_LIST);
//    }
//
//    @Override
//    public boolean matches(HttpServletRequest request) {
//        return false;
//    }
//
//    @Override
//    public List<Filter> getFilters() {
//        return null;
//    }
//
//
//}
