package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import com.ryansstore.store.dtos.UserRegisterRequest;
import com.ryansstore.store.entities.User;
import com.ryansstore.store.dtos.UserDto;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserRegisterRequest request);
}
