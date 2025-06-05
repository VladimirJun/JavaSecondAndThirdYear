package org.example.demo1.mapper;

import org.example.demo1.dto.subject.SubjectDto;
import org.example.demo1.dto.subject.UpdateSubjectDto;
import org.example.demo1.entity.SubjectEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper extends CommonMapper<SubjectEntity, SubjectDto> {
    @Override
    SubjectEntity mapToEntity(SubjectDto subjectDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateSubjectDto updateSubjectDto, @MappingTarget SubjectEntity subject);

    @Override
    SubjectDto mapToDto(SubjectEntity subject);

    @Override
    List<SubjectEntity> mapToEntities(List<SubjectDto> dtos);

    @Override
    List<SubjectDto> mapToDtos(List<SubjectEntity> entities);
}