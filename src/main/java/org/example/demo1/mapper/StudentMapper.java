package org.example.demo1.mapper;

import org.example.demo1.dto.StudentDto;

import org.example.demo1.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper extends CommonMapper<StudentEntity, StudentDto> {
    @Override
    @Mapping(target = "group.id", source = "groupId")
    StudentEntity mapToEntity(StudentDto studentDto);

    @Override
    @Mapping(target = "groupId", source = "group.id")
    StudentDto mapToDto(StudentEntity student);

    @Override
    List<StudentEntity> mapToEntities(List<StudentDto> dtos);

    @Override
    List<StudentDto> mapToDtos(List<StudentEntity> entities);

}