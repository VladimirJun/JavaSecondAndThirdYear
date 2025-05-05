package org.example.demo1.mapper;

import org.example.demo1.dto.GroupDto;
import org.example.demo1.entity.GroupEntity;
import org.example.demo1.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


import java.util.List;

//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
@Mapper(componentModel = "spring")
public interface GroupMapper extends CommonMapper<GroupEntity, GroupDto> {
    @Override
    @Mapping(target = "students", qualifiedByName = "mapToEntityFromStudentsIds", source = "studentsIds")
    GroupEntity mapToEntity(GroupDto groupDto);

    @Named("mapToEntityFromStudentsIds")
    default List<StudentEntity> mapToEntityFromStudentsIds(List<Long> studentsIds) {
        if (studentsIds == null) {
            return null;
        }
        return studentsIds.stream()
                .map(StudentEntity::new)
                .toList();
    }

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