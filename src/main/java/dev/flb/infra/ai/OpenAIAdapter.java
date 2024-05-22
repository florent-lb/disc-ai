package dev.flb.infra.ai;

import dev.flb.service.AIPort;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

/*
*  Ne pas oublier de lier vos Tools !
* */
@RegisterAiService(tools = {UserTool.class})
public interface OpenAIAdapter extends AIPort {


    @Override
    @SystemMessage("""
        You may choose a tool or answer the text NOT_FOUND.      \s
        If you don't found any valid tool, return only the text : NOT_FOUND
        """)
    String action(@UserMessage String message);
}
