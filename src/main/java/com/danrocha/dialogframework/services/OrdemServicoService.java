package com.danrocha.dialogframework.services;

import com.danrocha.dialogframework.entities.OrdemServico;
import com.danrocha.dialogframework.exceptions.NegocioException;
import com.danrocha.dialogframework.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Service
public class OrdemServicoService implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Autowired
    private transient OrdemServicoRepository ordemRepo;

    public void salvarOuAtualizar(OrdemServico ordem) {
        this.ordemRepo.saveAndFlush(ordem);
    }


    public List<OrdemServico> listarOrdens() {
        return this.ordemRepo.findAll();
    }

    public void excluir(OrdemServico ordem) {
        this.ordemRepo.delete(ordem);
    }
}
