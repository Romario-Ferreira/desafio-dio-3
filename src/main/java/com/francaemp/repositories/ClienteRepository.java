package com.francaemp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.francaemp.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
