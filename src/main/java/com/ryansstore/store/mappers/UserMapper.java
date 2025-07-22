package com.ryansstore.store.mappers;

import org.mapstruct.Mapper;
import com.ryansstore.store.dtos.UserDto;
import com.ryansstore.store.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
