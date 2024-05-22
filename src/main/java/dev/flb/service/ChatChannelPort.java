package dev.flb.service;

import dev.flb.domain.User;

import java.util.List;

public interface ChatChannelPort {
    void open();

    void sendMessage(String json);

    List<User> listAllUsers();

    void close();
}
