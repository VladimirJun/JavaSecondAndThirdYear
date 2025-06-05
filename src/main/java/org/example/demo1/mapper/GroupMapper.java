package org.example.demo1.mapper;

import org.example.demo1.dto.group.CreateGroupDto;
import org.example.demo1.dto.group.GroupDto;
import org.example.demo1.dto.group.UpdateGroupDto;
import org.example.demo1.entity.GroupEntity;
import org.example.demo1.entity.StudentEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;


import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface GroupMapper extends CommonMapper<GroupEntity, GroupDto> {
    GroupEntity mapToEntityFromCreateDto(CreateGroupDto groupDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(UpdateGroupDto updateGroupDto, @MappingTarget GroupEntity group);

    @Override
    @Mapping(target = "studentsIds", qualifiedByName = "mapToDtoFromStudents", source = "students")
    GroupDto mapToDto(GroupEntity group);

    @Named("mapToDtoFromStudents")
    default List<Long> mapToDtoFromStudents(List<StudentEntity> students) {
        if (students == null) {
            return null;
        }
        return students.stream()
                .map(StudentEntity::getId)
                .toList();
    }

    @Override
    List<GroupEntity> mapToEntities(List<GroupDto> dtos);

    @Override
    List<GroupDto> mapToDtos(List<GroupEntity> entities);
}