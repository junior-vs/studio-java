package org.acme.cxf.siatr;

import br.gov.caixa.sid09.verificacontacpftitularidade.D09POVTFPort;
import br.gov.caixa.sid09.verificacontacpftitularidade.req.ObjectFactory;
import br.gov.caixa.sid09.verificacontacpftitularidade.resp.ProgramInterface;
import io.quarkiverse.cxf.annotation.CXFClient;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/sid09/verifica-titular")
public class VerificaContaCpfTitularidadeResource {

    @CXFClient("verificaContaCpfTitularidade")
    D09POVTFPort service;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response verificaContaCpfTitular(TitularidadeRequestForm form) {

        var objectFactory = new ObjectFactory();
        var entrada = objectFactory.createProgramInterfaceRequestEntrada();
        entrada.setNuConta(form.nuConta());
        entrada.setIcTitularidade(form.icTitularidade());
        entrada.setNuCpfCnpj(form.nuCpfCnpj());
        entrada.setTipoPessoa(form.tipoPessoa());


        var request = objectFactory.createProgramInterfaceRequest();
        request.setEntrada(entrada);
        var response = service.verificaContaCpfTitularidade(request);

        ProgramInterface.Response.Controle controle = response.getControle();

        return Response.ok(controle).build();
    }
}
