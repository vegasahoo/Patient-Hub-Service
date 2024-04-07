package com.patient.hub.ph.config.security.service;

import com.patient.hub.ph.config.security.vo.UserInfoUserDetails;
import com.patient.hub.ph.repository.userinfo.IUserDBRepo;
import com.patient.hub.ph.repository.userinfo.dto.UserInfo;
import com.patient.hub.ph.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoUserDetailService implements UserDetailsService {

    @Autowired
    private IUserDBRepo userDBRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> user = userDBRepo.findByUserName(username);
        return user.map(UserInfoUserDetails::new).orElseThrow(()-> new ResourceNotFoundException("User not found"));
    }
}
