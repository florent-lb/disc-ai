package dev.flb.infra.jpa;

import dev.flb.domain.User;
import dev.flb.service.UserPort;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import java.util.List;

@RequestScoped
public class JPAUserAdapter implements UserPort {

    @Inject
    UserEntityMapper userEntityMapper;

    public List<User> listAll() {
        return UserEntity.<UserEntity>streamAll()
                .map(userEntityMapper::fromEntity)
                .toList();
    }

}
