package service_test;


import org.example.entity.GroupEntity;
import org.example.entity.StudentEntity;
import org.example.exception.ServiceException;
import org.example.model.StudentModel;
import org.example.model.StudentStatus;
import org.example.repository.EntityRepository;
import org.example.request.AddStudentRequest;
import org.example.request.EditStudentRequest;
import org.example.request.IdRequest;
import org.example.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    private StudentService studentService;

    private final Map<Long, StudentEntity> studentStorage = new HashMap<>();
    private final Map<Long, GroupEntity> groupStorage = new HashMap<>();

    @BeforeEach
    void setUp() {
        EntityRepository<StudentEntity> studentRepository = new EntityRepository<>() {
            @Override
            public StudentEntity get(Long id) {
                return studentStorage.get(id);
            }

            @Override
            public List<StudentEntity> getAll() {
                return new ArrayList<>(studentStorage.values());
            }

            @Override
            public Long create(StudentEntity entity) {
                Long id = (long) (studentStorage.size() + 1);
                entity = new StudentEntity(id, entity.firstName(), entity.lastName(), entity.patronymic(), entity.status(), entity.groupId());
                studentStorage.put(id, entity);
                return id;
            }

            @Override
            public void update(StudentEntity entity) {
                studentStorage.put(entity.id(), entity);
            }

            @Override
            public void delete(Long id) {
                studentStorage.remove(id);
            }
        };

        EntityRepository<GroupEntity> groupRepository = new EntityRepository<>() {
            @Override
            public GroupEntity get(Long id) {
                return groupStorage.get(id);
            }

            @Override
            public List<GroupEntity> getAll() {
                return new ArrayList<>(groupStorage.values());
            }

            @Override
            public Long create(GroupEntity entity) {
                Long id = (long) (groupStorage.size() + 1);
                groupStorage.put(id, entity);
                return id;
            }

            @Override
            public void update(GroupEntity entity) {
                groupStorage.put(entity.id(), entity);
            }

            @Override
            public void delete(Long id) {
                groupStorage.remove(id);
            }
        };

        studentService = new StudentService(studentRepository, groupRepository);
    }

    @Test
    void testGetStudentsByGroup() {
        Long groupId = 1L;
        groupStorage.put(groupId, new GroupEntity(groupId, "Group A"));
        studentStorage.put(1L, new StudentEntity(1L, "John", "Doe", "", StudentStatus.ACTIVE, groupId));
        studentStorage.put(2L, new StudentEntity(2L, "Jane", "Doe", "", StudentStatus.ACADEMIC_VACATION, groupId));

        IdRequest request = new IdRequest(groupId);
        List<StudentModel> result = studentService.getStudentsByGroup(request);

        assertEquals(2, result.size());
        assertEquals("John", result.get(0).firstName());
        assertEquals("Jane", result.get(1).firstName());
    }

    @Test
    void testGetStudentById() {
        Long studentId = 1L;
        Long groupId = 2L;
        groupStorage.put(groupId, new GroupEntity(groupId, "Group B"));
        studentStorage.put(studentId, new StudentEntity(studentId, "John", "Doe", "", StudentStatus.ACTIVE, groupId));

        IdRequest request = new IdRequest(studentId);
        StudentModel result = studentService.getStudentById(request);

        assertNotNull(result);
        assertEquals("John", result.firstName());
        assertEquals("Group B", result.group().name());
    }

    @Test
    void testAddStudent() {
        Long groupId = 1L;
        groupStorage.put(groupId, new GroupEntity(groupId, "Group A"));

        AddStudentRequest request = new AddStudentRequest("Petrov", "Vladimir", "Iurievich", 1L, "ACTIVE");
        Long result = studentService.addStudent(request);

        assertNotNull(result);
        assertTrue(studentStorage.containsKey(result));
        assertEquals("Vladimir", studentStorage.get(result).firstName());
    }

    @Test
    void testEditStudent() {
        Long studentId = 1L;
        Long groupId = 2L;
        groupStorage.put(groupId, new GroupEntity(groupId, "Group B"));
        studentStorage.put(studentId, new StudentEntity(studentId, "Vova", "Ivanov", "Urich", StudentStatus.ACTIVE, groupId));

        EditStudentRequest request = new EditStudentRequest(studentId, "Vladimir", "Petrov", "Yurievich", groupId,"ACADEMIC_VACATION");
        studentService.editStudent(request);

        StudentEntity updatedStudent = studentStorage.get(studentId);
        assertNotNull(updatedStudent);
        assertEquals("Vladimir", updatedStudent.lastName());
        assertEquals(StudentStatus.ACADEMIC_VACATION, updatedStudent.status());
    }

    @Test
    void testDeleteStudent() {
        Long studentId = 1L;
        studentStorage.put(studentId, new StudentEntity(studentId, "Vladimir", "Petrov", "Yurievich", StudentStatus.ACADEMIC_VACATION, 1L));

        IdRequest request = new IdRequest(studentId);
        studentService.deleteStudent(request);

        assertFalse(studentStorage.containsKey(studentId));
    }

    @Test
    void testGetStudentById_GroupNotFound() {
        Long studentId = 1L;
        Long groupId = 2L;
        studentStorage.put(studentId, new StudentEntity(studentId, "Vladimir", "Petrov", "Yurievich", StudentStatus.ACADEMIC_VACATION, groupId));

        IdRequest request = new IdRequest(studentId);

        ServiceException exception = assertThrows(ServiceException.class, () -> studentService.getStudentById(request));
        assertEquals("No group entity with id 2 found", exception.getMessage());
    }

    @Test
    void testEditStudent_NotFound() {
        Long studentId = 1L;
        EditStudentRequest request = new EditStudentRequest(studentId, "Jane", null, null, 1L,"DROPPED_OUT");

        ServiceException exception = assertThrows(ServiceException.class, () -> studentService.editStudent(request));
        assertEquals("No student entity with id 1 found", exception.getMessage());
    }
}
