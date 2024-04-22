package br.com.alura.alurator;

import java.util.Map;

import br.com.alura.alurator.protocolo.Request;
import br.com.alura.alurator.reflexao.Reflexao;

public class Alurator {

	private String pacoteBase;

	public Alurator(String pacoteBase) {
		this.pacoteBase = pacoteBase;
	}

	public Object executa(String url) {
		// TODO - processa a requisicao executando o metodo
		// da classe em questao

		// Produto lista

		// produto -> roduto

		Request request = new Request(url);

		String nomeControle = request.getNomeControle();
		String nomeMetodo = request.getNomeMetodo();
		Map<String, Object> queryParams = request.getQueryParams();
		
		

		Object retorno = new Reflexao()
				.refleteClasse(pacoteBase + nomeControle)
				.criaInstancia()
				.getMetodo(nomeMetodo, queryParams)
				.comTratamentoDeExcecao((metodo, e)-> {
					System.out.println("Erro no método " + metodo.getName() + " da classe " + metodo.getDeclaringClass().getName() + ".\n\n");
                    throw new RuntimeException("ERRO!");
				})
				.invoca();

//		obj.metodo()

		System.out.println(retorno);

		return retorno;
	}
}
