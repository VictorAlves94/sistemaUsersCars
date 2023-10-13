package com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository;

import org.springframework.security.core.userdetails.UserDetails;

public interface LoginRepository {
    UserDetails findByLogin(String login);
}
