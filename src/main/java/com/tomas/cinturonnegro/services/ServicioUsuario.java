package com.tomas.cinturonnegro.services;

import com.tomas.cinturonnegro.models.User;
import com.tomas.cinturonnegro.repositories.RepositorioBase;
import com.tomas.cinturonnegro.repositories.RepositorioUser;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioUsuario extends ServicioBase<User> {
    private final RepositorioUser repositorioUser;

    public ServicioUsuario(RepositorioUser repositorioBase) {
        super(repositorioBase);
        this.repositorioUser = repositorioBase;
    }

    // register
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return repositorioUser.save(user);
    }

    public User findByEmail(String email) {
        return repositorioUser.findByEmail(email);
    }

    public User findUserById(Long id) {
        Optional<User> optionalUser = repositorioUser.findById(id);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    //login (autenticar usuario)
    public boolean authenticateUser(String email, String password) {
        // encontramos user por email
        User user = repositorioUser.findByEmail(email);
        // si no lo encuentra retorna false
        if(user == null) {
            return false;
        } else {
            // si el password coincide devolvemos true sino false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean emailExist(String email) {
        return repositorioUser.existsByEmail(email);
    }

    public long count() {
        return repositorioUser.count();
    }
}
