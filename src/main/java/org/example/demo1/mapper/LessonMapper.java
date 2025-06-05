package org.example.demo1.mapper;

import org.example.demo1.dto.lesson.LessonDto;
import org.example.demo1.entity.LessonEntity;
import org.example.demo1.entity.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;


import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LessonMapper extends CommonMapper<LessonEntity, LessonDto> {
    @Override
    @Mapping(target = "group.id", source = "groupId")
    @Mapping(target = "teacher.id", source = "teacherId")
    @Mapping(target = "studentAttendance", qualifiedByName = "mapToEntityFromMapAttendance", source = "attendance")
    LessonEntity mapToEntity(LessonDto lessonDto);

    @Named("mapToEntityFromMapAttendance")
    default Map<StudentEntity, Boolean> mapToEntityFromMapAttendance(Map<Long, Boolean> attendance) {
        if (attendance == null){
            return null;
        }
        return attendance.entrySet()
                .stream()
                .collect(toMap(
                        entry -> new StudentEntity(entry.getKey()),
                        Map.Entry::getValue
                ));
    }

    @Override
    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "attendance", qualifiedByName = "mapToDtoFromMapStudentAttendance", source = "studentAttendance")
    LessonDto mapToDto(LessonEntity lesson);

    @Named("mapToDtoFromMapStudentAttendance")
    default Map<Long, Boolean> mapToDtoFromMapStudentAttendance(Map<StudentEntity, Boolean> attendance) {
        if (attendance == null){
            return null;
        }
        return attendance.entrySet()
                .stream()
                .collect(toMap(
                        entry -> entry.getKey().getId(),
                        Map.Entry::getValue
                ));
    }

    @Override
    List<LessonEntity> mapToEntities(List<LessonDto> dtos);

    @Override
    List<LessonDto> mapToDtos(List<LessonEntity> entities);
}