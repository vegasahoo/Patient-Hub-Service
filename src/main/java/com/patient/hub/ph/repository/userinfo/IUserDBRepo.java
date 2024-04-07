package com.patient.hub.ph.repository.userinfo;

import com.patient.hub.ph.repository.userinfo.dto.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public interface IUserDBRepo extends JpaRepository<UserInfo, String> {
    Optional<UserInfo> findByUserName(String username);

}
