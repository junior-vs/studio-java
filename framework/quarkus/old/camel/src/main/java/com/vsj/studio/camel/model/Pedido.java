package com.vsj.studio.camel.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Pedido {
    public int id;
    public LocalDate dataCompra;
    public List<Item> itens;
    public Pagamento pagamento;
}
