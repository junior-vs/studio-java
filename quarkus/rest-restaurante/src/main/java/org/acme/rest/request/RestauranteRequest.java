package org.acme.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RestauranteRequest(@NotBlank @Size(max = 18) String cnpj,
                                 @NotBlank @Size(max = 255) String nome,
                                 @Size(max = 1000) String descricao,
                                 @Valid @JsonProperty("endereco")
                                 EnderecoRequest endereco) {
}
