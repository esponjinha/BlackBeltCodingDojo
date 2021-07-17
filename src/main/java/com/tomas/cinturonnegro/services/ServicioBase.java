package com.tomas.cinturonnegro.services;

import com.tomas.cinturonnegro.repositories.RepositorioBase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class ServicioBase<T> {
    RepositorioBase<T> repositorioBase;

    public ServicioBase(RepositorioBase repositorioBase) {
        this.repositorioBase = repositorioBase;
    }

    public List<T> allData() {
        return repositorioBase.findAll();
    }

    public T create(T entity) {
        return repositorioBase.save(entity);
    }

    public T findById(Long id) {
        Optional<T> tOptional = repositorioBase.findById(id);
        if(tOptional.isPresent()) {
            return tOptional.get();
        } else {
            return null;
        }
    }

    public T update(T entity) {
        return repositorioBase.save(entity);
    }

    public void delete(Long id) {
        repositorioBase.deleteById(id);
    }
}
