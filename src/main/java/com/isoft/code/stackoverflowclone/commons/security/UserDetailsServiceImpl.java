//package com.isoft.code.stackoverflowclone.commons.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {
//
//    private final UserRepository userRepository;
//    private final AuthenticationManager authenticationManager;
//    private final AccessLogService accessLogService;
//
//    @Autowired
//    public UserDetailsServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, AccessLogService accessLogService) {
//        this.userRepository = userRepository;
//        this.authenticationManager = authenticationManager;
//        this.accessLogService = accessLogService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        final TPUser user = userRepository.findByEmailIgnoreCase(email).orElseThrow(() -> new CustomException(String.format("User %s Not Found", email), HttpStatus.NOT_FOUND));
//        if(user.isTempRole() && LocalDateTime.now().isAfter(user.getTempRoleExpiryDate())) {
//            user.setRole(user.getDefaultRole());
//            userRepository.save(user);
//        }
//        return new UserDetailsImpl(user);
//    }
//
//    private TPUser loadUserByLoginPattern(String email, String countryCode, String phoneNumber) {
//        Optional<TPUser> existingUser = email != null ?
//                userRepository.findByEmailIgnoreCase(email) :
//                userRepository.findByCountryCodeAndPhoneNumber(countryCode, phoneNumber);
//
//        return existingUser.orElseThrow(() -> new CustomException("User Not Found", HttpStatus.NOT_FOUND));
//    }
//
//    public TPUser loadUserByLoginCommand(LoginCommand cmd) {
//        TPUser user = loadUserByLoginPattern(cmd.getEmail(), cmd.getCountryCode(), cmd.getPhoneNumber());
//        TPAccessLog accessLog = createUserAccessLog(cmd, user);
//        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), cmd.getPassword()));
//        accessLogService.updateUserAccessLog(accessLog.getId());
//        return user;
//    }
//
//    private TPAccessLog createUserAccessLog(LoginCommand cmd, TPUser user) {
//        TPAccessLog accessLog = new TPAccessLog();
//        accessLog.setLoginMode(cmd.getLoginMode());
//        accessLog.setSocialMediaChannel(cmd.getSocialMedia());
//        accessLog.setLoginTime(LocalDateTime.now());
//        accessLog.setUserId(user.getId());
//        accessLog.setCreatedById(user.getId());
//        accessLog.setSuccessfulLogin(false);
//        accessLogService.logUserEntry(accessLog);
//        return accessLog;
//    }
//
//    public TPUser loadUserByForgotPasswordCommand(ForgotPasswordCommand cmd) {
//        return loadUserByLoginPattern(cmd.getEmail(), cmd.getCountryCode(), cmd.getPhoneNumber());
//    }
//
//    @Override
//    public void onApplicationEvent(AuthenticationSuccessEvent event) {
//        UserDetails ud = (UserDetails) event.getAuthentication().getPrincipal();
//        userRepository.logUserEntry(ud.getUsername());
//    }
//}
