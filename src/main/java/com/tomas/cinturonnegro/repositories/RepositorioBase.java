package com.tomas.cinturonnegro.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface RepositorioBase<T> extends CrudRepository<T, Long> {
    List<T> findAll();
}
