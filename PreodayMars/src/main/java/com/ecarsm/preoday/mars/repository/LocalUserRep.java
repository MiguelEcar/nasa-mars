package com.ecarsm.preoday.mars.repository;

import com.ecarsm.preoday.mars.entity.LocalUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalUserRep extends JpaRepository<LocalUser, Integer> {

    public Optional<LocalUser> email(String email);
}
