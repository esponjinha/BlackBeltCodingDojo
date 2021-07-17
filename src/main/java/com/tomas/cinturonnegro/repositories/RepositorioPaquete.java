package com.tomas.cinturonnegro.repositories;

import com.tomas.cinturonnegro.models.Paquete;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPaquete extends RepositorioBase<Paquete> {
    Paquete findByPackageNameContaining(String packageName);
}
