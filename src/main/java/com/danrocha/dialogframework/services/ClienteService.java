package com.danrocha.dialogframework.services;

import com.danrocha.dialogframework.entities.Cliente;
import com.danrocha.dialogframework.repositories.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepo;

    public Cliente clientePorId(Long id) {
        return this.clienteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente nao encontrado"));
    }

    public List<Cliente> pesquisarClientesPeloNome(String nome) {
        return clienteRepo.findByNomeContaining(nome);
    }

}
