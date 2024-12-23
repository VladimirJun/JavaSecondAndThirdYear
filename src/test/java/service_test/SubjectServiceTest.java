package service_test;


import org.example.entity.SubjectEntity;
import org.example.exception.ServiceException;
import org.example.model.SubjectModel;
import org.example.repository.EntityRepository;
import org.example.request.AddSubjectRequest;
import org.example.request.EditSubjectRequest;
import org.example.request.IdRequest;
import org.example.service.SubjectService;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SubjectServiceTest {
    private final Map<Long, SubjectEntity> subjectStorage = new HashMap<>();

    public EntityRepository<SubjectEntity> subjectRepository = new EntityRepository<>() {
        @Override
        public SubjectEntity get(Long id) {
            return subjectStorage.get(id);
        }

        @Override
        public List<SubjectEntity> getAll() {
            return new ArrayList<>(subjectStorage.values());
        }

        @Override
        public Long create(SubjectEntity entity) {
            Long id = (long) (subjectStorage.size() + 1);
            entity = new SubjectEntity(id, entity.name());
            subjectStorage.put(id, entity);
            return id;
        }

        @Override
        public void update(SubjectEntity entity) {
            subjectStorage.put(entity.id(), entity);
        }

        @Override
        public void delete(Long id) {
            subjectStorage.remove(id);
        }
    };
    @Test
    void getAll() {
        SubjectService subjectService = new SubjectService(subjectRepository);
        subjectRepository.create(new SubjectEntity(1L, "Math"));
        subjectRepository.create(new SubjectEntity(2L, "Programming"));
        Collection<SubjectModel> result = subjectService.getSubjects();
        assertEquals(2, result.size());
        assertTrue(result.stream().anyMatch(l -> l.id().equals(1L)));
        assertTrue(result.stream().anyMatch(l -> l.id().equals(2L)));
    }
    @Test
    void getSubjectById() {
        SubjectService subjectService = new SubjectService(subjectRepository);
        subjectRepository.create(new SubjectEntity(1L,"English"));
        SubjectModel result = subjectService.getSubjectById(new IdRequest(1L));
        assertEquals( 1L,result.id());
    }
    @Test
    void getSubjectById_invalid(){
        SubjectService subjectService = new SubjectService(subjectRepository);
        SubjectModel result = subjectService.getSubjectById(new IdRequest(1L));
        assertNull(result);
    }

    @Test
    void addSubject() {
        SubjectService subjectService = new SubjectService(subjectRepository);
        AddSubjectRequest request = new AddSubjectRequest("math (again no fantasy)");
        Long result = subjectService.addSubject(request);
        assertEquals(1L,result);
    }

    @Test
    void editSubject(){
        SubjectService subjectService = new SubjectService(subjectRepository);
        AddSubjectRequest request0 = new AddSubjectRequest("Practice");
        subjectService.addSubject(request0);
        EditSubjectRequest request = new EditSubjectRequest(1L,"BGD");
        subjectService.editSubject(request);
        SubjectModel res_after = subjectService.getSubjectById(new IdRequest(1L));
        assertEquals("BGD",res_after.name());
    }
    @Test
    void editSubject_notFound(){
        SubjectService subjectService = new SubjectService(subjectRepository);
        assertThrows(ServiceException.class, () -> subjectService.editSubject(new EditSubjectRequest(1L,"BGD")));
    }

    @Test
    void deleteTeacher(){
        SubjectService subjectService = new SubjectService(subjectRepository);
        AddSubjectRequest request0 = new AddSubjectRequest("OOD");
        subjectService.addSubject(request0);
        subjectService.deleteSubject(new IdRequest(1L));
        assertNull(subjectService.getSubjectById(new IdRequest(1L)));
    }

}
