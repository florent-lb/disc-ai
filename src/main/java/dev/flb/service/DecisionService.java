package dev.flb.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

@RequestScoped
@Slf4j
public class DecisionService {

    @Inject
    AIPort aiPort;

    @Inject
    ChatChannelPort chatChannelPort;

    public void getDecision(String message) {
        chatChannelPort.sendMessage(aiPort.action(message));
    }

}
