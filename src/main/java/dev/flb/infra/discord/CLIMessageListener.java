package dev.flb.infra.discord;

import dev.flb.service.DecisionService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.control.ActivateRequestContext;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

@RequiredArgsConstructor
@ApplicationScoped
public class CLIMessageListener extends ListenerAdapter {
    @Inject
    DecisionService decisionService;
    @Override
    @ActivateRequestContext
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getChannel().getName().equalsIgnoreCase("bot-command")) {
            decisionService.getDecision(event.getMessage().getContentDisplay());
        }
    }


}
