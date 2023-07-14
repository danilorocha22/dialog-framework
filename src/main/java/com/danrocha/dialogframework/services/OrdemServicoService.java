package com.danrocha.dialogframework.services;

import com.danrocha.dialogframework.entities.OrdemServico;
import com.danrocha.dialogframework.exceptions.NegocioException;
import com.danrocha.dialogframework.repositories.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Service
public class OrdemServicoService implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Autowired
    private transient OrdemServicoRepository ordemRepo;

    public void salvarOuAtualizar(OrdemServico ordem) throws NegocioException {
        if (Objects.isNull(ordem)) {
            throw new NegocioException("Não foi possível salvar ou atualizar a Ordem de Serviço");
        }
        this.ordemRepo.saveAndFlush(ordem);
    }


    public Page<OrdemServico> listarOrdens(Pageable pageable) throws NegocioException {
        if (Objects.isNull(pageable)) {
            throw new NegocioException("Não foi possível listar as Ordens de Serviços");
        }
        return this.ordemRepo.findAll(pageable);
    }

    public void excluir(OrdemServico ordem) throws NegocioException {
        if (Objects.isNull(ordem)) {
            throw new NegocioException("Não foi possível excluir a Ordem de Serviço");
        }
        this.ordemRepo.delete(ordem);
    }

    public long count() {
        return this.ordemRepo.count();
    }
}
