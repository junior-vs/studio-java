package com.demo.estoque.modelo.item;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.StringJoiner;

public class Filtro {

	private TipoItem tipo;

	private String nome;

	public Filtro() {
	}

	public Filtro(TipoItem tipo, String nome) {
		this.tipo = tipo;
		this.nome = nome;
	}

	public TipoItem getTipo() {
		return tipo;
	}

	public void setTipo(TipoItem tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return new StringJoiner(", ", Filtro.class.getSimpleName() + "[", "]")
				.add("tipo=" + tipo)
				.add("nome='" + nome + "'")
				.toString();
	}
}
