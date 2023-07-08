package com.danrocha.dialogframework.controllers;

import com.danrocha.dialogframework.entities.Cliente;
import com.danrocha.dialogframework.entities.OrdemServico;
import com.danrocha.dialogframework.services.OrdemServicoService;
import com.danrocha.dialogframework.util.FacesMessages;
import jakarta.validation.constraints.NotBlank;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import java.io.Serial;
import java.io.Serializable;

@Controller
@Scope("view")
public class OrdemServicoController implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    @Autowired
    private OrdemServicoService ordemService;

    private transient OrdemServico ordemServico;

    @Autowired
    private FacesMessages messages;


    /*Getters e Setters*/
    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    @NotBlank
    public String getNomeCliente() {
        return this.ordemServico.getCliente() == null ? null : this.ordemServico.getCliente().getNome();
    }

    public void setNomeCliente(String nomeCliente) {
        //TODO: verificar se precisa fazer isso
    }

    /*Métodos*/
    public void inicializar() {
        this.ordemServico = new OrdemServico();
    }

    public void clienteSelecionado(SelectEvent<Cliente> event) {
        Cliente cliente = event.getObject();
        this.ordemServico.setCliente(cliente);
    }

    public void salvar() {
        this.ordemService.salvar(this.ordemServico);
        this.messages.info("Ordem de serviço cadastrada com sucesso, para: "+ this.ordemServico.getCliente().getNome());
        this.ordemServico = new OrdemServico();
    }


}
