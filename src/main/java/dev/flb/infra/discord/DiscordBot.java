package dev.flb.infra.discord;

import dev.flb.domain.User;
import dev.flb.service.ChatChannelPort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.Jsonb;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.RestAction;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class DiscordBot implements ChatChannelPort {

    private final CLIMessageListener cliMessageListener;
    private final Jsonb jsonb;
    private final UserDiscordMapper userMapper;
    @ConfigProperty(name = "discord.token")
    String token;
    @ConfigProperty(name = "discord.channel.userRequest")
    String userRequestChannelName;
    private Optional<JDA> jda = Optional.empty();
    private TextChannel userRequestChannel;

    @Override
    public void open() {
        connectBot();
    }

    @Override
    public void sendMessage(String message) {
        connectBot();
        jda.orElseThrow()
                .getTextChannels()
                .stream().filter(textChannel -> textChannel.getName().equalsIgnoreCase("bot-answer"))
                .findFirst()
                .map(textChannel -> textChannel.sendMessage(message))
                .map(RestAction::submit)
                .ifPresent(messageCompletableFuture -> {
                    try {
                        messageCompletableFuture.get();
                    } catch (InterruptedException | ExecutionException e) {
                        throw new RuntimeException(e);
                    }
                });

    }

    private void connectBot() {
        if (jda.isEmpty()) {
            try {
                jda = Optional.of(JDABuilder.createDefault(token)
                        .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                        .addEventListeners(cliMessageListener)
                        .build()
                        .awaitReady());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<User> listAllUsers() {
        connectBot();
        return jda.get()
                .getUsers().stream().map(userMapper::fromDiscord).toList();
    }

}



