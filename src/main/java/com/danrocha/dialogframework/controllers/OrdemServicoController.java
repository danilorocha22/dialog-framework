package com.danrocha.dialogframework.controllers;

import com.danrocha.dialogframework.entities.Cliente;
import com.danrocha.dialogframework.entities.OrdemServico;
import com.danrocha.dialogframework.exceptions.NegocioException;
import com.danrocha.dialogframework.services.OrdemServicoService;
import com.danrocha.dialogframework.util.FacesMessages;
import jakarta.validation.constraints.NotBlank;
import org.primefaces.event.SelectEvent;
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

    private final LazyDataModel<OrdemServico> ordensModel;

    private long count;


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
    }

    public LazyDataModel<OrdemServico> getOrdensModel() {
        return ordensModel;
    }

    public long getCount() {
        return count;
    }

    /*Métodos*/
    public void novaOrdemServico() {
        this.ordemServico = new OrdemServico();
    }

    public void clienteSelecionado(SelectEvent<Cliente> event) {
        Cliente cliente = event.getObject();
        this.ordemServico.setCliente(cliente);
    }

    public void salvarOuAtualizar() {
        try {
            String nomeCliente = this.ordemServico.getCliente().getNome();
            this.ordemService.salvarOuAtualizar(this.ordemServico);

            if (Objects.isNull(this.ordemServico.getId())) {
                this.messages.info("Ordem de serviço cadastrada com sucesso para: " + nomeCliente);
            } else {
                this.messages.info("Ordem de serviço atualizada com sucesso para: " + nomeCliente);
            }

            this.novaOrdemServico();
        } catch (NegocioException e) {
            this.messages.errorSticky(e.getMessage());
        }
    }

    public void editarOrdemServico(OrdemServico ordem) {
        this.setOrdemService(ordem);
    }

    public void excluirOrdemServico(OrdemServico ordem) {
        try {
            String nomeCliente = ordem.getCliente().getNome();
            this.ordemService.excluir(ordem);
            this.messages.info(String.format("Ordem de serviço da empresa %s, excluída com sucesso.", nomeCliente));
        } catch (NegocioException e) {
            this.messages.errorSticky(e.getMessage());
        }
    }

    public void limparFormulario() {
        this.novaOrdemServico();
    }

    public void totalRegistros() {
        this.count = this.ordemService.count();
    }

    public OrdemServicoController() {
        this.ordensModel = new LazyDataModel<>() {

            @Override
            public int count(Map<String, FilterMeta> filterBy) {
                return filterBy.size();
            }

            @Override
            public List<OrdemServico> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
                Pageable pageable;
                String sortField;
                String sortOrder;

                if (sortBy.isEmpty()) {
                    pageable = PageRequest.of((first / pageSize), pageSize);
                } else {
                    sortField = sortBy.keySet().toString().replaceAll("[^a-zA-Z]", "");
                    sortOrder = getSortOrder(sortBy.get(sortField).getOrder().name());
                    pageable = PageRequest.of((first / pageSize), pageSize, Sort.Direction.fromString(sortOrder), sortField);
                }

                setRowCount((int) getCount());
                return ordemService.listarOrdens(pageable).stream().toList();
            }
        };
    }

    private String getSortOrder(String sortOrder) {
        return switch (sortOrder) {
            case "ASCENDING" -> "ASC";
            case "DESCENDING" -> "DESC";
            default -> throw new IllegalStateException("Valor inesperado: " + sortOrder);
        };
    }


}
