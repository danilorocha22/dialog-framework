package com.danrocha.dialogframework.controllers;

import com.danrocha.dialogframework.entities.Cliente;
import com.danrocha.dialogframework.services.ClienteService;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DialogFrameworkOptions;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Controller
@Scope("view")
public class SelecaoClienteController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private transient ClienteService clienteService;

    private String nome = "";

    public LazyDataModel<Cliente> clientesModel;

    private long count;

    /*Getters e Setters*/
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


    public LazyDataModel<Cliente> getClientesModel() {
        return clientesModel;
    }

    public long getCount() {
        return count;
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

    public void totalRegistros() {
        this.count = this.clienteService.count();
    }

    public SelecaoClienteController() {
        this.clientesModel = new LazyDataModel<>() {
            @Override
            public int count(Map<String, FilterMeta> filterBy) {
                return filterBy.size();
            }

            @Override
            public List<Cliente> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
                Pageable pageable = PageRequest.of((first / pageSize), pageSize);
                setRowCount((int) getCount());

                if (!getNome().isBlank()) {
                    return clienteService.pesquisarClientesPeloNomeSemPaginacao(nome);
                }

                return clienteService.pesquisarClientesPeloNomeComPaginacao(getNome(), pageable).stream().toList();
            }
        };

    }
}
