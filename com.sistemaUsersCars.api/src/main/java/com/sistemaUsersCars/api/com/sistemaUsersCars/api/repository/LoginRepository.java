package com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Users, Long> {
    UserDetails findByLogin(String login);

}
