package com.isoft.code.stackoverflowclone.service.impl.util;

import com.isoft.code.stackoverflowclone.commons.security.UserDetailsImpl;
import com.isoft.code.stackoverflowclone.entity.Users;
import com.isoft.code.stackoverflowclone.repository.AnswerRepository;
import com.isoft.code.stackoverflowclone.repository.QuestionRepository;
import com.isoft.code.stackoverflowclone.repository.UserRepository;
import java.util.Collections;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

@SpringBootTest
@TestInstance(Lifecycle.PER_METHOD)
public class TestUtil {

  @Autowired
  protected UserRepository userRepository;

  @Autowired
  protected AnswerRepository answerRepository;

  protected void initDummySecurityContext() {
    SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());
    Users user = new Users();
    user.setName("anonymous-user");
    userRepository.save(user);
    SecurityContextHolder.getContext().setAuthentication(
        new AnonymousAuthenticationToken(
            "anonymous-key",
            new UserDetailsImpl(user),
            Collections.singleton(new SimpleGrantedAuthority("ROLE_TEST"))
        )
    );
  }
}
