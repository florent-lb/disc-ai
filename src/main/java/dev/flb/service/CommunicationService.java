package dev.flb.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class CommunicationService {

    private final ChatChannelPort chatChannelPort;

    public void openChannel()
    {
        chatChannelPort.open();
    }

}
