package com.demo.estoque.exceptions;

import jakarta.xml.ws.WebFault;

@WebFault(name="AutorizacaoFault", messageName="AutorizacaoFault")
public class AuthorizationException extends Exception {

  public AuthorizationException(String message) {
    super(message);
  }

  public String getFaultInfo(){
    return "token invalido";
  }
}
