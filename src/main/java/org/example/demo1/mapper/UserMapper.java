package org.example.demo1.mapper;

import org.example.demo1.dto.user.UserDto;
import org.example.demo1.entity.user.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends CommonMapper<UserEntity, UserDto> {
    @Override
    UserEntity mapToEntity(UserDto userDto);

    @Override
    UserDto mapToDto(UserEntity user);

    @Override
    List<UserEntity> mapToEntities(List<UserDto> dtos);

    @Override
    List<UserDto> mapToDtos(List<UserEntity> entities);
}