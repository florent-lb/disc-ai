package dev.flb.service;

import dev.flb.domain.User;

import java.util.List;

public interface UserPort {
     List<User> listAll();
}
