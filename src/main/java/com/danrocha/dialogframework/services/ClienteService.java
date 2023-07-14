package com.danrocha.dialogframework.services;

import com.danrocha.dialogframework.entities.Cliente;
import com.danrocha.dialogframework.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepo;

    public Cliente clientePorId(Long id) {
        return this.clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
    }

    public List<Cliente> pesquisarClientesPeloNomeSemPaginacao(String nome) {
        return this.clienteRepo.findClientesByNomeContaining(nome);
    }

    public Page<Cliente> pesquisarClientesPeloNomeComPaginacao(String nome, Pageable pageable) {
        return this.clienteRepo.findClientesByNomeContaining(nome, pageable);
    }

    public long count() {
        return this.clienteRepo.count();
    }


}
