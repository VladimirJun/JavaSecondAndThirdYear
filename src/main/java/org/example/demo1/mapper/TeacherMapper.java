package org.example.demo1.mapper;

import org.example.demo1.dto.TeacherDto;
import org.example.demo1.entity.TeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeacherMapper extends CommonMapper<TeacherEntity, TeacherDto> {
    @Override
    TeacherEntity mapToEntity(TeacherDto teacherDto);

    @Override
    TeacherDto mapToDto(TeacherEntity teacher);

    @Override
    List<TeacherEntity> mapToEntities(List<TeacherDto> dtos);

    @Override
    List<TeacherDto> mapToDtos(List<TeacherEntity> entities);
}