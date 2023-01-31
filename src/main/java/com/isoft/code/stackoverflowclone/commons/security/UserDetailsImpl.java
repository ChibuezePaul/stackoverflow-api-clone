//package com.isoft.code.stackoverflowclone.commons.security;
//
//import com.baudland.teconplus.usermgmt.accessctrl.TPPrivilege;
//import com.baudland.teconplus.usermgmt.user.TPUser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.stream.Collectors;
//
//public class UserDetailsImpl implements UserDetails {
//
//    private static final long serialVersionUID = 1145488804034890513L;
//
//    private final TPUser user;
//
//    @Autowired
//    public UserDetailsImpl(TPUser user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return user.getRole().getPrivileges().stream()
//                .map(TPPrivilege::getName)
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return !user.isLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return !user.isLocked();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return !user.isLocked();
//    }
//
//    public TPUser getUser() {
//        return user;
//    }
//}
