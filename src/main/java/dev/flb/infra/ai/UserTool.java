package dev.flb.infra.ai;

import dev.flb.domain.User;
import dev.flb.service.ChatChannelPort;
import dev.flb.service.UserPort;
import dev.langchain4j.agent.tool.Tool;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.json.bind.Jsonb;
import lombok.RequiredArgsConstructor;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class UserTool {

    private final UserPort userPort;
    private final ChatChannelPort chatChannelPort;
    private final Jsonb jsonb;

    @Tool("When a user want to list users in database. this is the default list of users.")
    public void listUsers() {
        shareUsers(userPort.listAll());
    }

    @Tool("When a user want to list users in the discord.")
    public void listUsersInChannel() {
        shareUsers(chatChannelPort.listAllUsers());
    }

    private void shareUsers(List<User> users) {
        chatChannelPort.sendMessage(jsonb.toJson(users));
    }
}
