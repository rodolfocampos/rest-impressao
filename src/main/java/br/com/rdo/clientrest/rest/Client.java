package br.com.rdo.clientrest.rest;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.rdo.clientrest.model.Impressao;

@Component
public class Client {

	public Impressao[] getArrayImpressao() {

		Long idLoja = 1L;
		String url = "http://wwww.com.br/impressao/" + idLoja;

		try {
			RestTemplate restTemplate = new RestTemplate();

			Impressao[] impressao = restTemplate.getForObject(url, Impressao[].class);
			return impressao;

		} catch (Exception e) {
			e.printStackTrace();
			Impressao[] impressao = new Impressao[0];
			return impressao;
		}
	}

	public void setStatusImpresso(Long idImpressao) throws InterruptedException {

		String url = "http://www.com.br/impressao/status/" + idImpressao;
		try {
			RestTemplate restTemplate = new RestTemplate();
			Boolean resposta = restTemplate.getForObject(url, Boolean.class);
			if (resposta == false || resposta == null) {
				Thread.sleep(5000);
				setStatusImpresso(idImpressao);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Thread.sleep(10000);
			setStatusImpresso(idImpressao);
		}

	}

}
