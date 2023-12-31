package com.danrocha.dialogframework.repositories;

import com.danrocha.dialogframework.entities.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findClientesByNomeContaining(String nome);

    Page<Cliente> findClientesByNomeContaining(String nome, Pageable pageable);

    @Override
    long count();
}
