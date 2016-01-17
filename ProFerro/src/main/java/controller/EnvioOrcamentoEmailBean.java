package controller;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.velocity.tools.generic.NumberTool;

import com.outjected.email.api.MailMessage;
import com.outjected.email.impl.templating.velocity.VelocityTemplate;

import model.Orcamento;
import util.jsf.FacesUtil;
import util.mail.Mailer;

@Named
@RequestScoped
public class EnvioOrcamentoEmailBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	@OrcamentoEdicao
	private Orcamento orcamento;
	
	@Inject
	private Mailer mailer;
	
	public void enviar(){
		MailMessage message = mailer.novaMensagem();
	
		message.to(this.orcamento.getCliente().getEmail())
		.subject("Orcamento " + this.orcamento.getId())
		.bodyHtml(new VelocityTemplate(getClass().getResourceAsStream("/emails/orcamento.template")))
		.put("orcamento", this.orcamento)
		.put("numberTool", new NumberTool())
		.put("locale", new Locale("pt", "BR"))
		.charset("utf-8")

		.send();
		
		FacesUtil.addInfoMessage("Orcamento enviado por e-mail com sucesso.");
	}
	
}
