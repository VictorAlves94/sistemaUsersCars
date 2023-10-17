package com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

}
