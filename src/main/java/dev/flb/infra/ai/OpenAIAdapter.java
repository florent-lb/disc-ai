package dev.flb.infra.ai;

import dev.flb.service.AIPort;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
@RegisterAiService(tools = {UserTool.class})
public interface OpenAIAdapter extends AIPort {


    @Override
    @SystemMessage("""
        You must choose tool, no textual response is needed.
        
        If you don't found any valid tool, please return only the text : NOT_FOUND
        """)
    String action(@UserMessage String message);
}
