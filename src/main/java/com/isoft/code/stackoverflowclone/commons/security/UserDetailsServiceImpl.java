package com.isoft.code.stackoverflowclone.commons.security;

import com.isoft.code.stackoverflowclone.dto.SignInDto;
import com.isoft.code.stackoverflowclone.entity.Users;
import com.isoft.code.stackoverflowclone.exception.CustomException;
import com.isoft.code.stackoverflowclone.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CustomException(String.format("User %s Not Found", email), HttpStatus.NOT_FOUND));
        return new UserDetailsImpl(user);
    }

}
