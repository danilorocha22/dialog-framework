package com.danrocha.dialogframework.controllers;

import com.danrocha.dialogframework.entities.Cliente;
import com.danrocha.dialogframework.services.ClienteService;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DialogFrameworkOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Controller
@Scope("view")
public class SelecaoClienteController implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Autowired
    private transient ClienteService clienteService;

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
        DialogFrameworkOptions opcoes = DialogFrameworkOptions.builder()
                .modal(true)
                .resizable(false)
                .contentHeight("700")
                .build();
        PrimeFaces.current().dialog().openDynamic("pesquisa-cliente", opcoes, null);
    }

    public void selecionar(Cliente cliente) {
        PrimeFaces.current().dialog().closeDynamic(cliente);
    }

    public void pesquisar() {
        this.clientesFiltrados = this.clienteService.pesquisarClientesPeloNome(this.nome);
    }

}
