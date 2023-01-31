//package com.isoft.code.stackoverflowclone.commons.auth;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.exceptions.JWTDecodeException;
////import com.isoft.code.stackoverflowclone.commons.security.UserDetailsServiceImpl;
//import com.isoft.code.stackoverflowclone.exception.CustomException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//import java.util.Base64;
//import java.util.Date;
//
//@Slf4j
//@Component
//public class JwtTokenProvider {
//
//    private static final String AUTHORIZATION = "Authorization";
//    private static final String TOKEN_PREFIX = "Bearer ";
//
//    @Value("${jwt.token.secret-key}")
//    private String secretKey;
//
//    @Value("${jwt.token.validity}")
//    private long validityInMilliseconds;
//
//    private final UserDetailsServiceImpl userDetailsService;
//
//    public JwtTokenProvider(UserDetailsServiceImpl userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @PostConstruct
//    protected void init() {
//        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
//    }
//
//    public String createToken(String email) {
//        Date now = new Date();
//        Date validity = new Date(now.getTime() + validityInMilliseconds);
//        return JWT.create()
//                .withSubject(email)
//                .withIssuedAt(now)
//                .withExpiresAt(validity)
//                .sign(Algorithm.HMAC256(secretKey));
//    }
//
//    protected Authentication getAuthentication(String token) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(getUsername(token));
//        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
//    }
//
//    private String getUsername(String token) {
//        return JWT.decode(token).getSubject();
//    }
//
//    protected String resolveToken(HttpServletRequest req) {
//        String token = null;
//        String bearerToken = req.getHeader(AUTHORIZATION);
//        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
//            token = bearerToken.substring(7);
//        }
//        return token;
//    }
//
//    protected boolean validateToken(String token, HttpServletRequest servletRequest) {
//        String errorMessage;
//        try {
//            JWT.decode(token);
//            return true;
//        } catch (JWTDecodeException e) {
//            log.error("ExpiredJwtException JWT Token Error ", e);
//            servletRequest.setAttribute("actual-error", e.getMessage());
//            throw new CustomException(e.getMessage(), HttpStatus.UNAUTHORIZED);
//        }
//    }
//
//}
