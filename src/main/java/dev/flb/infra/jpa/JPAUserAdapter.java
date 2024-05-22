package dev.flb.infra.jpa;

import dev.flb.domain.User;
import dev.flb.service.UserPort;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@RequestScoped
public class JPAUserAdapter implements UserPort {

    @Inject
    UserEntityMapper userEntityMapper;

    @Inject
    EntityManager entityManager;

    public List<User> listAll() {
        return
                entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class)
                        .getResultStream()
                        .map(userEntityMapper::fromEntity)
                        .toList();
    }

}
