package org.acme.service;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.infra.mappers.RestauranteMapper;
import org.acme.persistence.model.Restaurante;
import org.acme.persistence.repository.RestauranteRepository;

import java.util.List;

@ApplicationScoped
public class RestauranteService {

    final RestauranteRepository restauranteRepository;

    //constructor
    public RestauranteService(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    public Uni<List<Restaurante>> buscaTodos() {
     //   return restauranteRepository.findAll().list().map(this.restauranteMapper::toResponse);
return null;
    }
}
