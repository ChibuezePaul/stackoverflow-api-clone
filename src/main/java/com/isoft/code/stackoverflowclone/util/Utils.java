package com.isoft.code.stackoverflowclone.util;

import com.isoft.code.stackoverflowclone.commons.security.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Utils {
  public UserDetailsImpl getUserDetails() {
    return (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
