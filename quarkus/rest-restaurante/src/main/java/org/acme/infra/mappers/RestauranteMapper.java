package org.acme.infra.mappers;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import org.acme.persistence.model.Restaurante;
import org.acme.rest.request.RestauranteRequest;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(config = QuarkusMappingConfig.class)
public interface RestauranteMapper {
   RestauranteRequest toResponse(Restaurante panacheEntityBases);
}
