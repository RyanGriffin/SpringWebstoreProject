package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import com.ryansstore.store.dtos.UserUpdateRequest;
import com.ryansstore.store.dtos.UserRegisterRequest;
import com.ryansstore.store.dtos.UserDto;
import com.ryansstore.store.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserRegisterRequest request);

    void updateEntity(UserUpdateRequest request, @MappingTarget User user);
}
