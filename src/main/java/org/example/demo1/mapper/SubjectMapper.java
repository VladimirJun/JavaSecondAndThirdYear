package org.example.demo1.mapper;

import org.example.demo1.dto.SubjectDto;
import org.example.demo1.entity.SubjectEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubjectMapper extends CommonMapper<SubjectEntity, SubjectDto>{
    @Override
    SubjectEntity mapToEntity(SubjectDto subjectDto);

    @Override
    SubjectDto mapToDto(SubjectEntity subject);

    @Override
    List<SubjectEntity> mapToEntities(List<SubjectDto> dtos);

    @Override
    List<SubjectDto> mapToDtos(List<SubjectEntity> entities);
}