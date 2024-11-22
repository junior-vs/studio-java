package org.acme.rest.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoRequest(@NotBlank @Size(max = 9) String cep,
                              @NotBlank @Size(max = 300) String endereco,
                              @NotBlank String cidade,
                              @NotBlank String estado,
                              @NotBlank String pais,
                              String complemento,
                              String bairro
) {
}
