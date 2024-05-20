package dev.flb.infra.discord;

import dev.flb.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.JAKARTA;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = JAKARTA, unmappedTargetPolicy = ERROR)
public interface UserDiscordMapper {

    @Mapping(target = "birthDate", ignore = true)
    User fromDiscord(net.dv8tion.jda.api.entities.User user);


}
