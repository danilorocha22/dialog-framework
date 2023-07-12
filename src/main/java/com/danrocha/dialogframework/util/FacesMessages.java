package com.danrocha.dialogframework.util;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.FacesMessage.Severity;
import jakarta.faces.context.FacesContext;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
public class FacesMessages implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    public void info(String mensagem) {
        this.add(mensagem, FacesMessage.SEVERITY_INFO);
    }

    public void warn(String mensagem) {
        this.add(mensagem, FacesMessage.SEVERITY_WARN);
    }

    public void error(String mensagem) {
        this.add(mensagem, FacesMessage.SEVERITY_ERROR);
    }

    public void fatal(String mensagem) {
        this.add(mensagem, FacesMessage.SEVERITY_FATAL);
    }

    public void infoSticky(String mensagem) {
        this.sticky(FacesMessage.SEVERITY_INFO, "Sucesso", mensagem);
    }

    public void warnSticky(String mensagem) {
        this.sticky(FacesMessage.SEVERITY_WARN, "Aviso", mensagem);
    }

    public void errorSticky(String mensagem) {
        this.sticky(FacesMessage.SEVERITY_ERROR, "Erro", mensagem);
    }

    public void fatalSticky(String mensagem) {
        this.sticky(FacesMessage.SEVERITY_FATAL, "Erro Grave", mensagem);
    }

    private void sticky(Severity severity, String titulo, String mensagem) {
        FacesContext.getCurrentInstance().addMessage("sticky-key", new FacesMessage(severity, titulo, mensagem));
    }

    private void add(String mensagem, Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(mensagem);
        msg.setSeverity(severity);
        context.addMessage(null, msg);
    }

}
