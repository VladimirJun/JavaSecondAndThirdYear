package org.example.demo1.service.impl;


import org.example.demo1.dto.student.CreateStudentDto;
import org.example.demo1.dto.student.StudentDto;
import org.example.demo1.dto.student.UpdateStudentDto;
import org.example.demo1.entity.GroupEntity;
import org.example.demo1.entity.StudentEntity;
import org.example.demo1.entity.user.Role;
import org.example.demo1.exception.NotFoundException;
import org.example.demo1.mapper.StudentMapper;
import org.example.demo1.repository.GroupRepository;
import org.example.demo1.repository.StudentRepository;
import org.example.demo1.service.StudentService;
import org.example.demo1.service.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private static final String ENTITY = "Student";

    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final UserService userService;

    public StudentServiceImpl(StudentMapper studentMapper,
                              StudentRepository studentRepository,
                              GroupRepository groupRepository,
                              UserService userService) {
        this.studentMapper = studentMapper;
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    @Override
    public Long addStudent(CreateStudentDto createStudentDto) {
        this.validateStudentGroup(createStudentDto.groupId());

        StudentEntity student = this.studentMapper.mapToEntity(createStudentDto);

        this.studentRepository.save(student);
        this.userService.createUser(createStudentDto.email(), createStudentDto.password(), Role.ROLE_STUDENT, student);

        return student.getId();
    }

    @Override
    public void editStudent(Long studentId, UpdateStudentDto updateStudentDto) {
        StudentEntity student = this.studentRepository.findById(studentId).orElseThrow(
                () -> new NotFoundException(ENTITY, studentId)
        );
        this.validateStudentGroup(updateStudentDto.groupId());

        this.updateEntityFromDto(updateStudentDto, student);
        this.studentRepository.save(student);
    }

    private void updateEntityFromDto(UpdateStudentDto updateStudentDto, StudentEntity student) {
        GroupEntity group = this.groupRepository.getReferenceById(updateStudentDto.groupId());
        student.setGroup(group);
        student.setName(updateStudentDto.name());
        student.setSurname(updateStudentDto.surname());
        student.setPatronymic(updateStudentDto.patronymic());
        student.setStatusOfStudents(updateStudentDto.statusOfStudents());
    }

    private void validateStudentGroup(Long groupId) {
        if (!this.groupRepository.existsById(groupId)) {
            throw new NotFoundException("Group", groupId);
        }
    }

    @Override
    public void deleteStudent(Long id) {
        StudentEntity student = this.studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        this.studentRepository.delete(student);
        this.userService.deleteUserByProfile(Role.ROLE_STUDENT, student);
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto getStudentById(Long id) {
        StudentEntity student = this.studentRepository.findById(id).orElseThrow(
                () -> new NotFoundException(ENTITY, id)
        );
        return this.studentMapper.mapToDto(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getByGroupId(Long id) {
        GroupEntity group = this.groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Group", id)
        );

        List<StudentEntity> studentsByGroup = this.studentRepository.findStudentByGroup(group);
        return this.studentMapper.mapToDtos(studentsByGroup);
    }
}