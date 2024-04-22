package br.com.alura.alurator.reflexao;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Stream;

public class ManipuladorObjeto {

	private Object instancia;

	public ManipuladorObjeto(Object instancia) {
		this.instancia = instancia;
	}

	public ManipuladorMetodo getMetodo(String nomeMetodo, Map<String, Object> params) {

		Stream<Method> metodos = Stream.of(instancia.getClass().getDeclaredMethods());
		Method metodoSelecionado = metodos.filter(
				metodo -> metodo.getName().equals(nomeMetodo) && metodo.getParameterCount() == params.values().size()
						&& Stream.of(metodo.getParameters())
								.allMatch(args -> params.keySet().contains(args.getName())
										&& params.get(args.getName()).getClass().equals(args.getType())))
				.findFirst().orElseThrow(() -> new RuntimeException("Medodo não encontrado"));

		return new ManipuladorMetodo(instancia, metodoSelecionado, params);

	}

}
