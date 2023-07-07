package com.danrocha.dialogframework.services;

import com.danrocha.dialogframework.entities.OrdemServico;
import com.danrocha.dialogframework.repositories.OrdemServicoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServicoService implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private OrdemServicoRepository ordemRepo;

    public OrdemServico salvar(OrdemServico ordem) {
        return this.ordemRepo.save(ordem);
    }


}
