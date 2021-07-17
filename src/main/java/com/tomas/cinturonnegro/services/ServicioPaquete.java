package com.tomas.cinturonnegro.services;

import com.tomas.cinturonnegro.models.Paquete;
import com.tomas.cinturonnegro.repositories.RepositorioPaquete;
import org.springframework.stereotype.Service;

@Service
public class ServicioPaquete extends ServicioBase<Paquete> {
    private final RepositorioPaquete repositorioPaquete;

    public ServicioPaquete(RepositorioPaquete repositorioBase) {
        super(repositorioBase);
        this.repositorioPaquete = repositorioBase;
    }

}
