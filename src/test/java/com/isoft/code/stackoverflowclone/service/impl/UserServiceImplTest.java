package com.isoft.code.stackoverflowclone.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import com.isoft.code.stackoverflowclone.commons.auth.JwtTokenProvider;
import com.isoft.code.stackoverflowclone.dto.SearchUserDto;
import com.isoft.code.stackoverflowclone.dto.SignInDto;
import com.isoft.code.stackoverflowclone.dto.SignUpDto;
import com.isoft.code.stackoverflowclone.dto.UserDto;
import com.isoft.code.stackoverflowclone.entity.Users;
import com.isoft.code.stackoverflowclone.service.impl.util.TestUtil;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

class UserServiceImplTest extends TestUtil {

  @Autowired
  @InjectMocks
  private UserServiceImpl underTest;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @MockBean
  private JwtTokenProvider jwtTokenProvider;

  @Test
  void signUp() {
    SignUpDto dto = new SignUpDto();
    dto.setDisplayName("chibueze paul");
    dto.setEmail("chibuezepaul2@gmail.com");
    dto.setPassword("chibueze-paul");

    UserDto newUser = underTest.signUp(dto);

    assertEquals(dto.getEmail(), newUser.getEmail());
    assertEquals(dto.getDisplayName(), newUser.getDisplayName());
    assertTrue(userRepository.findByEmail(dto.getEmail()).isPresent());
  }

  @Test
  void signIn() {
    Users user = new Users();
    user.setEmail("cp@mail.com");
    user.setPassword(passwordEncoder.encode("cp"));
    userRepository.save(user);

    SignInDto dto = new SignInDto();
    dto.setEmail(user.getEmail());
    dto.setPassword("cp");

    UserDto userDto = underTest.signIn(dto);

    assertNotNull(userDto);
    assertEquals(dto.getEmail(), userDto.getEmail());
    verify(jwtTokenProvider, atLeastOnce()).createToken(userDto.getEmail(), userDto.getId());
  }

  @Test
  void searchUsers_shouldReturnEmptyListWhenNoUserExistsInDatabase() {
    SearchUserDto dto = new SearchUserDto();
    dto.setEmail("cp@mail.com");
    dto.setName("cpaul");

    List<UserDto> users = underTest.searchUsers(dto);
    assertTrue(users.isEmpty());
  }

  @Test
  void searchUsers_shouldReturnUsersWhenTheyMatchSearchParams() {
    Users userWithSearchEmail = new Users();
    userWithSearchEmail.setEmail("Cp@mail.com");
    userRepository.save(userWithSearchEmail);

    Users userWithSearchedName = new Users();
    userWithSearchedName.setName("Cpaul");
    userRepository.saveAll(Arrays.asList(userWithSearchEmail, userWithSearchedName));

    SearchUserDto dto = new SearchUserDto();
    dto.setEmail("cp@mail.com");
    dto.setName("cpaul");

    List<UserDto> users = underTest.searchUsers(dto);
    assertFalse(users.isEmpty());
    assertEquals(2, users.size());
    assertEquals(2, userRepository.findAll().size());
  }
}