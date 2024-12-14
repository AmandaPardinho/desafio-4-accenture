package br.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.api.model.Address;

public interface CepRepository extends JpaRepository<Address, String> {
    
}