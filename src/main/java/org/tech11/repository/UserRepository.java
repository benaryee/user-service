package org.tech11.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.tech11.model.entity.User;

import java.util.Optional;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {

    public Optional<User> findByUsername(String username) {
        return find("username", username).firstResultOptional();
    }
    public Optional<User> findById(String id) {
        return find("id", id).firstResultOptional();
    }
}
