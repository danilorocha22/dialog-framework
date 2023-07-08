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

    public void info(String msg) {
        this.add(msg, FacesMessage.SEVERITY_INFO);
    }

    public void warning(String msg) {
        this.add(msg, FacesMessage.SEVERITY_WARN);
    }

    public void error(String msg) {
        this.add(msg,FacesMessage.SEVERITY_ERROR);
    }

    private void add(String mensagem, Severity severity) {
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(mensagem);
        msg.setSeverity(severity);
        context.addMessage(null, msg);
    }

}
