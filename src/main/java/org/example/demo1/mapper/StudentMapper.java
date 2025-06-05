package org.example.demo1.mapper;

import org.example.demo1.dto.student.CreateStudentDto;
import org.example.demo1.dto.student.StudentDto;
import org.example.demo1.entity.StudentEntity;
import org.example.demo1.mapper.CommonMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StudentMapper extends CommonMapper<StudentEntity, StudentDto> {

    @Mapping(target = "group.id", source = "groupId")
    StudentEntity mapToEntity(CreateStudentDto createStudentDto);

    @Override
    @Mapping(target = "groupId", source = "group.id")
    StudentDto mapToDto(StudentEntity student);

    @Override
    List<StudentDto> mapToDtos(List<StudentEntity> entities);
}