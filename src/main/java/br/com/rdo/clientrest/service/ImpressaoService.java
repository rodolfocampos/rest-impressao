package br.com.rdo.clientrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.rdo.clientrest.model.Impressao;
import br.com.rdo.clientrest.rest.Client;

@Component
public class ImpressaoService {

	@Autowired
	private Client client;
	@Autowired
	private PrintUtilities printUtilities;

	@Scheduled(fixedDelay = 5000)
	public void executarMetodos() {
		Impressao[] arrayImpressao = client.getArrayImpressao();
		if (arrayImpressao.length > 0) {
			imprimirArrayImpressao(arrayImpressao);
		}
	}

	public void imprimirArrayImpressao(Impressao[] arrayImpressao) {

		for (Impressao impressao : arrayImpressao) {
			imprimirTexto(impressao);
		}
	}

	public void imprimirTexto(Impressao impressao) {

		try {
			if (printUtilities.imprimirTexto(impressao.getContent()))
				client.setStatusImpresso(impressao.getId());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
