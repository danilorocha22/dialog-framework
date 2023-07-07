package com.danrocha.dialogframework.controllers;

import com.danrocha.dialogframework.entities.Cliente;
import com.danrocha.dialogframework.services.ClienteService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.primefaces.PrimeFaces;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("view")
@AllArgsConstructor
@NoArgsConstructor
public class SelecaoClienteController implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private ClienteService clienteService;

    private String nome;

    private List<Cliente> clientesFiltrados;


    /*Getters e Setters*/
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    /*Métodos*/
    public void abrirDialogModal() {
        Map<String, Object> opcoes = new  HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 500);
        PrimeFaces.current().dialog().openDynamic("SelecaoCliente", opcoes, null);
    }

    public void selecionar(Cliente cliente) {
        PrimeFaces.current().dialog().closeDynamic(cliente);
    }

    public void pesquisar(String nome) {
        this.clientesFiltrados = this.clienteService.pesquisarClientesPeloNome(nome);
    }

}
