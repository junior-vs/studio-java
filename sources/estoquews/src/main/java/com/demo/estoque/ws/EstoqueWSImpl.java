package com.demo.estoque.ws;

import com.demo.estoque.exceptions.AuthorizationException;
import com.demo.estoque.modelo.item.Filtro;
import com.demo.estoque.modelo.item.Filtros;
import com.demo.estoque.modelo.item.Item;
import com.demo.estoque.modelo.item.ItemDao;
import com.demo.estoque.modelo.item.ListaItens;
import com.demo.estoque.modelo.usuario.TokenDao;
import com.demo.estoque.modelo.usuario.TokenUsuario;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService(serviceName = "EstoqueWS", portName = "estoque", name = "estoque",
    endpointInterface = "com.demo.estoque.ws.EstoqueWS")
public class EstoqueWSImpl implements EstoqueWS {

  private ItemDao dao = new ItemDao();

  @Override
  public ListaItens getItens(Filtros filtros) {
    System.out.println("Chamando getItens()");
    List<Filtro> lista = filtros.getLista();
    List<Item> itensResultado = dao.todosItens(lista);
    return new ListaItens(itensResultado);
  }

  @Override
  public Item cadastrarItem(TokenUsuario tokenUsuario, Item item) throws AuthorizationException {
    boolean ehValido = new TokenDao().ehValido(tokenUsuario);

    if(!ehValido){
      throw new AuthorizationException("Erro de Validação do token!");
    }
    System.out.println(tokenUsuario);
    System.out.println("Cadastrando " + item);

    this.dao.cadastrar(item);
    return item;
  }


}
