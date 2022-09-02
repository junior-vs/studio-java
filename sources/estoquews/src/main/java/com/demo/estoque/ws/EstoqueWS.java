package com.demo.estoque.ws;

import com.demo.estoque.exceptions.AuthorizationException;
import com.demo.estoque.modelo.item.Filtros;
import com.demo.estoque.modelo.item.Item;
import com.demo.estoque.modelo.item.ListaItens;
import com.demo.estoque.modelo.usuario.TokenUsuario;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;

@WebService()
public interface EstoqueWS {



    @WebMethod(operationName = "todosOsItens")
    @WebResult(name = "item")
    public ListaItens getItens(@WebParam(name = "filtros") Filtros filtros);


    @WebMethod(operationName="CadastrarItem")
    public Item cadastrarItem(@WebParam(name = "tokenUsuario", header = true) TokenUsuario tokenUsuario, @WebParam(name="item") Item item)
        throws AuthorizationException;

}