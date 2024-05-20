package dev.flb.infra.jpa;

import dev.flb.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.JAKARTA;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = JAKARTA, unmappedTargetPolicy = ERROR)
public interface UserEntityMapper {


    @Mapping(target = "name", source = "lastName")
    User fromEntity(UserEntity userEntity);

}
