package com.sistemaUsersCars.api.com.sistemaUsersCars.api.repository;

import com.sistemaUsersCars.api.com.sistemaUsersCars.api.entity.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarsRepository extends JpaRepository<Cars,Long > {
}
