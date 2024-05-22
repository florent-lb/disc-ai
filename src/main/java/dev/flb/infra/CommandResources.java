package dev.flb.infra;

import dev.flb.service.CommunicationService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import lombok.RequiredArgsConstructor;

@Path("/command")
@RequiredArgsConstructor
@ApplicationScoped
public class CommandResources {

    private final CommunicationService communicationService;

    @POST
    @Path("start")
    @Consumes(MediaType.WILDCARD)
    public void startBot() {
        communicationService.openChannel();
    }

}
