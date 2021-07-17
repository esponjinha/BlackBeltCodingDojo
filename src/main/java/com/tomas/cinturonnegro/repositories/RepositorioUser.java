package com.tomas.cinturonnegro.repositories;

import com.tomas.cinturonnegro.models.User;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioUser extends RepositorioBase<User> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
