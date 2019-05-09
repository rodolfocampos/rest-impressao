package br.com.rdo.clientrest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Impressao {

	@JsonProperty("id")
	private Long id;
	@JsonProperty("content")
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
