package service_test;


import org.example.entity.TeacherEntity;
import org.example.exception.ServiceException;
import org.example.model.TeacherModel;
import org.example.repository.EntityRepository;
import org.example.request.AddTeacherRequest;
import org.example.request.EditTeacherRequest;
import org.example.request.IdRequest;
import org.example.service.TeacherService;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherServiceTest {
    private final Map<Long, TeacherEntity> teacherStorage = new HashMap<>();
    public EntityRepository<TeacherEntity> teacherRepository = new EntityRepository<>() {
        @Override
        public TeacherEntity get(Long id) {
            return teacherStorage.get(id);
        }

        @Override
        public List<TeacherEntity> getAll() {
            return new ArrayList<>(teacherStorage.values());
        }

        @Override
        public Long create(TeacherEntity entity) {
            Long id = (long) (teacherStorage.size() + 1);
            entity = new TeacherEntity(id, entity.firstName(), entity.patronymic(), entity.lastName());
            teacherStorage.put(id, entity);
            return id;
        }

        @Override
        public void update(TeacherEntity entity) {
            teacherStorage.put(entity.id(), entity);
        }

        @Override
        public void delete(Long id) {
            teacherStorage.remove(id);
        }
    };
    private final TeacherService teacherService = new TeacherService(teacherRepository);

    @Test
    void getTeachers(){
        teacherRepository.create(new TeacherEntity(1L,"Ashaev","Igor","Viktorovich"));
        teacherRepository.create(new TeacherEntity(2L,"Berezin","Andrey","Andreevich"));
        Collection<TeacherModel> result = teacherService.getTeachers();
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(l -> l.id().equals(1L)));
        assertTrue(result.stream().anyMatch(l -> l.id().equals(2L)));
    }

    @Test
    void getTeacherById(){
        teacherRepository.create(new TeacherEntity(1L,"Ashaev","Igor","Viktorovich"));
        TeacherModel result = teacherService.getTeacherById(new IdRequest(1L));
        assertEquals( 1L,result.id());
    }

    @Test
    void getTeacherById_invalid(){
        TeacherModel result = teacherService.getTeacherById(new IdRequest(1L));
        assertNull(result);
    }

    @Test
    void addTeacher(){
        AddTeacherRequest request = new AddTeacherRequest("Ashaev","Igor","Viktorovich");
        Long result = teacherService.addTeacher(request);
        assertEquals(1L,result);
    }

    @Test
    void editTeacher(){
        AddTeacherRequest request0 = new AddTeacherRequest("Petrov","Vladimir","Iurevich");
        teacherService.addTeacher(request0);
        EditTeacherRequest request = new EditTeacherRequest(1L,"Ivanov","ivan","Viktorovich");
        teacherService.editTeacher(request);
        TeacherModel res_after = teacherService.getTeacherById(new IdRequest(1L));
        assertEquals("Ivanov",res_after.firstName());
    }
    @Test
    void editTeacher_notFound(){
        assertThrows(ServiceException.class, () -> teacherService.editTeacher(new EditTeacherRequest(1L,"Ivanov","ivan","Viktorovich")));
    }

    @Test
    void deleteTeacher(){
        AddTeacherRequest request0 = new AddTeacherRequest("Petrov","Vladimir","Iurevich");
        teacherService.addTeacher(request0);
        teacherService.deleteTeacher(new IdRequest(1L));
        assertNull(teacherService.getTeacherById(new IdRequest(1L)));
    }
}
