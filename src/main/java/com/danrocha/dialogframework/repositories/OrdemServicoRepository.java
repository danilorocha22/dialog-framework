package com.danrocha.dialogframework.repositories;

import com.danrocha.dialogframework.entities.OrdemServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Long> {

    @Override
    long count();
}
