package com.ryansstore.store.users;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserRegisterRequest request);

    void updateEntity(UserUpdateRequest request, @MappingTarget User user);
}
