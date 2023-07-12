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
import java.util.List;
import java.util.Objects;

@Controller
@Scope("view")
public class OrdemServicoController implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Autowired
    private OrdemServicoService ordemService;

    private transient OrdemServico ordemServico;

    @Autowired
    private FacesMessages messages;

    private List<OrdemServico> ordens;


    /*Getters e Setters*/
    public OrdemServico getOrdemServico() {
        return ordemServico;
    }

    public void setOrdemService(OrdemServico ordem) {
        this.ordemServico = ordem;
    }

    @NotBlank
    public String getNomeCliente() {
        return this.ordemServico.getCliente() == null ? null : this.ordemServico.getCliente().getNome();
    }

    public void setNomeCliente(String nomeCliente) {
        this.ordemServico.getCliente().setNome(nomeCliente);
    }

    public List<OrdemServico> getOrdens() {
        return ordens;
    }

    /*Métodos*/
    public void novaOrdemServico() {
        this.ordemServico = new OrdemServico();
    }

    public void refreshDadosTabela() {
        this.ordens = this.ordemService.listarOrdens();
    }

    public void clienteSelecionado(SelectEvent<Cliente> event) {
        Cliente cliente = event.getObject();
        this.ordemServico.setCliente(cliente);
    }

    public void salvarOuAtualizar() {
        if (Objects.isNull(this.ordemServico.getId())) {
            this.messages.info("Ordem de serviço cadastrada com sucesso para: " + this.ordemServico.getCliente().getNome());
        } else {
            this.messages.info("Ordem de serviço atualizada com sucesso para: " + this.ordemServico.getCliente().getNome());
        }
        this.ordemService.salvarOuAtualizar(this.ordemServico);
        this.novaOrdemServico();
        this.refreshDadosTabela();
    }

    public void editarOrdemServico(OrdemServico ordem) {
        this.setOrdemService(ordem);
    }

    public void excluirOrdemServico(OrdemServico ordem) {
        if (Objects.nonNull(ordem)) {
            this.messages.info(
                    String.format("Ordem de serviço da empresa %s, excluída com sucesso.", ordem.getCliente().getNome()));
            this.ordemService.excluir(ordem);
            this.refreshDadosTabela();
        } else {
            this.messages.warnSticky("Não foi possível excluir a ordem de serviço");
        }
    }

    public void limparFormulario() {
        this.novaOrdemServico();
    }

}
