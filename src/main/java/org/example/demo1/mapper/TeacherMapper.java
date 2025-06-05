package org.example.demo1.mapper;

import org.example.demo1.dto.teacher.CreateTeacherDto;
import org.example.demo1.dto.teacher.TeacherDto;
import org.example.demo1.dto.teacher.UpdateTeacherDto;
import org.example.demo1.entity.TeacherEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TeacherMapper extends CommonMapper<TeacherEntity, TeacherDto> {

    TeacherEntity mapToEntity(CreateTeacherDto createTeacherDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateTeacherDto updateTeacherDto, @MappingTarget TeacherEntity teacher);

    @Override
    TeacherDto mapToDto(TeacherEntity teacher);

//    List<Teacher> mapToEntities(List<CreateTeacherDto> dtos);

    @Override
    List<TeacherDto> mapToDtos(List<TeacherEntity> entities);
}