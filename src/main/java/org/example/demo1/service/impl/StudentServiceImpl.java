package org.example.demo1.service.impl;


import org.example.demo1.dto.StudentDto;
import org.example.demo1.entity.GroupEntity;
import org.example.demo1.entity.StudentEntity;
import org.example.demo1.mapper.StudentMapper;
import org.example.demo1.repository.StudentRepository;
import org.example.demo1.service.StudentService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String ENTITY = "Student";

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public StudentServiceImpl(StudentMapper studentMapper,
                              StudentRepository studentRepository,
                              GroupRepository groupRepository) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
    }

    @Override
    @Transactional
    public Long addStudent(StudentDto studentRequest) {
        try {

            if (!this.groupRepository.existsById(studentRequest.groupId())) {
                throw new NotFoundException("Group", studentRequest.groupId());
            }

            StudentEntity student = this.studentMapper.mapToEntity(studentRequest);
            return this.studentRepository.save(student).getId();
        } catch (Exception e) {

            throw new ServiceException("Service error on add student", e);
        }
    }

    @Override
    @Transactional
    public void editStudent(StudentDto studentRequest) {
        try {

            if (!this.studentRepository.existsById(studentRequest.id())) {
                throw new NotFoundException(ENTITY, studentRequest.id());
            }

            if (!this.groupRepository.existsById(studentRequest.groupId())) {
                throw new NotFoundException(ENTITY, studentRequest.groupId());
            }

            StudentEntity student = this.studentMapper.mapToEntity(studentRequest);
            this.studentRepository.save(student);
        } catch (Exception e) {

            throw new ServiceException("Service error on edit student", e);
        }
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        try {

            if (!this.studentRepository.existsById(id)) {
                throw new NotFoundException(ENTITY, id);
            }
            this.studentRepository.deleteById(id);
        } catch (Exception e) {

            throw new ServiceException("Service error on delete student", e);
        }
    }

    @Override
    public StudentDto getStudentById(Long id) {
        try {

            StudentEntity student = this.studentRepository.findById(id).orElseThrow(
                    () -> new ChangeSetPersister.NotFoundException(ENTITY, id)
            );
            return this.studentMapper.mapToDto(student);
        } catch (Exception e) {

            throw new ServiceException("Service error on get student by id", e);
        }
    }

    @Override
    public List<StudentDto> getByGroupId(Long id) {
        try {

            GroupEntity group = this.groupRepository.findById(id).orElseThrow(
                    () -> new ChangeSetPersister.NotFoundException("Group", id)
            );
            List<StudentEntity> studentsByGroup = group.getStudents();

            return this.studentMapper.mapToDtos(studentsByGroup);
        } catch (Exception e) {

            throw new ServiceException("Service error on get group by id", e);
        }
    }
}