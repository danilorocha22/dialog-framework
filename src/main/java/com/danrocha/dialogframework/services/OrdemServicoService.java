package com.danrocha.dialogframework.services;

import com.danrocha.dialogframework.entities.OrdemServico;
import com.danrocha.dialogframework.repositories.OrdemServicoRepository;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;

@Service
public class OrdemServicoService implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Autowired
    private transient OrdemServicoRepository ordemRepo;

    public OrdemServico salvar(OrdemServico ordem) {
        return this.ordemRepo.save(ordem);
    }


}
