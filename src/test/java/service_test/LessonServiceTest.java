package service_test;


import org.example.entity.*;
import org.example.model.LessonModel;
import org.example.model.StudentStatus;
import org.example.repository.AttendanceRepository;
import org.example.repository.EntityRepository;
import org.example.repository.LessonRepository;
import org.example.request.*;
import org.example.service.LessonService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LessonServiceTest {
    private final Map<Long, StudentEntity> studentStorage = new HashMap<>();
    private final Map<Long, GroupEntity> groupStorage = new HashMap<>();
    private final Map<Long, LessonEntity> lessonStorage = new HashMap<>();
    private final Map<Long, TeacherEntity> teacherStorage = new HashMap<>();
    private final Map<Long, AttendanceEntity> attendanceStorage = new HashMap<>();
    private final Map<Long, SubjectEntity> subjectStorage = new HashMap<>();
    public LessonRepository lessonRepository = new LessonRepository() {
        @Override
        public LessonEntity get(Long id) {
            return lessonStorage.get(id);
        }

        @Override
        public Long create(LessonEntity entity) {
            Long id = (long) (lessonStorage.size() + 1);
            entity = new LessonEntity(id, entity.subjectId(), entity.date(), entity.number(), entity.teacherId(), entity.groupId());
            lessonStorage.put(id, entity);
            return id;
        }

        @Override
        public void update(LessonEntity entity) {
            lessonStorage.put(entity.id(), entity);
        }

        @Override
        public void delete(Long id) {
            lessonStorage.remove(id);
        }

        @Override
        public Collection<LessonEntity> getLessonsByTutorId(Long tutorId, LocalDate start, LocalDate end) {
            return lessonStorage.values().stream()
                    .filter(lesson -> lesson.teacherId().equals(tutorId) &&
                            (lesson.date().isEqual(start) || lesson.date().isAfter(start)) &&
                            (lesson.date().isEqual(end) || lesson.date().isBefore(end)))
                    .toList();
        }

        @Override
        public Collection<LessonEntity> getLessonsByGroupId(Long groupId, LocalDate start, LocalDate end) {
            return lessonStorage.values().stream()
                    .filter(lesson -> lesson.groupId().equals(groupId) &&
                            (lesson.date().isEqual(start) || lesson.date().isAfter(start)) &&
                            (lesson.date().isEqual(end) || lesson.date().isBefore(end)))
                    .toList();
        }

        @Override
        public void deleteLessonsByTutorId(Long tutorId) {
            lessonStorage.values().removeIf(lesson -> lesson.teacherId().equals(tutorId));
        }

        @Override
        public void deleteLessonsByGroupId(Long groupId) {
            lessonStorage.values().removeIf(lesson -> lesson.groupId().equals(groupId));
        }
    };


    public EntityRepository<GroupEntity> groupRepository = new EntityRepository<>() {
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

    public EntityRepository<StudentEntity> studentRepository = new EntityRepository<>() {
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
    public AttendanceRepository attendanceRepository = new AttendanceRepository() {
        @Override
        public AttendanceEntity get(Long id) {
            return attendanceStorage.get(id);
        }

        @Override
        public Long create(AttendanceEntity entity) {
            Long id = (long) (attendanceStorage.size() + 1);
            entity = new AttendanceEntity(id, entity.lessonId(), entity.studentsId());
            attendanceStorage.put(id, entity);
            return id;
        }

        @Override
        public void update(AttendanceEntity entity) {
            attendanceStorage.put(entity.id(), entity);
        }

        @Override
        public void delete(Long id) {
            attendanceStorage.remove(id);
        }
    };

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
    void getLessonsByGroup() {
        LessonService lessonService = new LessonService(lessonRepository,studentRepository,groupRepository,teacherRepository,subjectRepository,attendanceRepository);
        Long groupId = 1L;
        String startDate = "01.12.2024";
        String endDate = "15.12.2024";
        List<Long> STUDENTS_ID = new ArrayList<>(List.of(1L,2L));
        groupRepository.create(new GroupEntity(1L, "group A"));
        studentRepository.create(new StudentEntity(1L,"Petrov","Vladimir","Iurevich", StudentStatus.ACTIVE,1L));
        studentRepository.create(new StudentEntity(1L,"Ivanov","Ivan","Iurevich", StudentStatus.ACTIVE,1L));
        attendanceRepository.create(new AttendanceEntity(1L,1L,STUDENTS_ID));
        attendanceRepository.create(new AttendanceEntity(2L,1L,STUDENTS_ID));
        subjectRepository.create(new SubjectEntity(1L,"Math"));
        teacherRepository.create(new TeacherEntity(1L,"Ashaev","Igor","Viktorovich"));
        lessonRepository.create(new LessonEntity(1L, 1L, LocalDate.of(2024, 12, 5), 1, 1L, groupId));
        lessonRepository.create(new LessonEntity(2L, 1L, LocalDate.of(2024, 12, 10), 2, 1L, groupId));

        GetLessonsByGroupRequest request = new GetLessonsByGroupRequest(startDate, endDate,groupId);
        Collection<LessonModel> lessons = lessonService.getLessonsByGroup(request);

        assertEquals(2, lessons.size());
        assertTrue(lessons.stream().anyMatch(l -> l.id().equals(1L)));
        assertTrue(lessons.stream().anyMatch(l -> l.id().equals(2L)));
    }
    @Test
    void getLessonById_notFound() {
        LessonService lessonService = new LessonService(lessonRepository,studentRepository,groupRepository,teacherRepository,subjectRepository,attendanceRepository);

        Long lessonId = 999L;

        IdRequest request = new IdRequest(lessonId);
        LessonModel result = lessonService.getLessonById(request);

        assertNull(result);
    }

    @Test
    void getLessonsByTeacher() {
        LessonService lessonService = new LessonService(lessonRepository,studentRepository,groupRepository,teacherRepository,subjectRepository,attendanceRepository);
        Long groupId = 1L;
        String startDate = "01.12.2024";
        String endDate = "15.12.2024";
        List<Long> STUDENTS_ID = new ArrayList<>(List.of(1L,2L));
        groupRepository.create(new GroupEntity(1L, "group A"));
        studentRepository.create(new StudentEntity(1L,"Petrov","Vladimir","Iurevich", StudentStatus.ACTIVE,1L));
        studentRepository.create(new StudentEntity(1L,"Ivanov","Ivan","Iurevich", StudentStatus.ACTIVE,1L));
        attendanceRepository.create(new AttendanceEntity(1L,1L,STUDENTS_ID));
        attendanceRepository.create(new AttendanceEntity(2L,1L,STUDENTS_ID));
        subjectRepository.create(new SubjectEntity(1L,"Math"));
        teacherRepository.create(new TeacherEntity(1L,"Ashaev","Igor","Viktorovich"));
        lessonRepository.create(new LessonEntity(1L, 1L, LocalDate.of(2024, 12, 5), 1, 1L, groupId));
        lessonRepository.create(new LessonEntity(2L, 1L, LocalDate.of(2024, 12, 10), 2, 1L, groupId));

        GetLessonsByTeacherRequest request = new GetLessonsByTeacherRequest(startDate, endDate,1L);
        Collection<LessonModel> lessons = lessonService.getLessonsByTeacher(request);

        assertEquals(2, lessons.size());
        assertTrue(lessons.stream().anyMatch(l -> l.id().equals(1L)));
        assertTrue(lessons.stream().anyMatch(l -> l.id().equals(2L)));
    }

    @Test
    void getLessonById() {
        LessonService lessonService = new LessonService(lessonRepository,studentRepository,groupRepository,teacherRepository,subjectRepository,attendanceRepository);
        Long groupId = 1L;
        List<Long> STUDENTS_ID = new ArrayList<>(List.of(1L));
        groupRepository.create(new GroupEntity(1L, "group A"));
        studentRepository.create(new StudentEntity(1L,"Petrov","Vladimir","Iurevich", StudentStatus.ACTIVE,1L));
        attendanceRepository.create(new AttendanceEntity(1L,1L,STUDENTS_ID));
        subjectRepository.create(new SubjectEntity(1L,"Math"));
        teacherRepository.create(new TeacherEntity(1L,"Ashaev","Igor","Viktorovich"));
        lessonRepository.create(new LessonEntity(1L, 1L, LocalDate.of(2024, 12, 5), 1, 1L, groupId));
        IdRequest request = new IdRequest(1L);
        LessonModel result = lessonService.getLessonById(request);
        assertEquals(1L, result.id());
        assertEquals(1, result.number());
    }

    @Test
    void addLessonTest(){
        subjectRepository.create(new SubjectEntity(1L,"Math"));
        String date = "01.12.2024";
        List<Long> STUDENTS_ID = new ArrayList<>(List.of(1L));
        groupRepository.create(new GroupEntity(1L, "group A"));
        studentRepository.create(new StudentEntity(1L,"Petrov","Vladimir","Iurevich", StudentStatus.ACTIVE,1L));
        studentRepository.create(new StudentEntity(2L,"Ivanov","Ivan","Iurevich", StudentStatus.ACTIVE,1L));
        LessonService lessonService = new LessonService(lessonRepository,studentRepository,groupRepository,teacherRepository,subjectRepository,attendanceRepository);
        AddLessonRequest request = new AddLessonRequest(1L,date,1,1L,1L,STUDENTS_ID);
        long result = lessonService.addLesson(request);
        assertEquals(1L, result);
    }

    @Test
    void editLessonTest(){
        subjectRepository.create(new SubjectEntity(1L,"Math"));
        Long groupId = 1L;
        String date = "01.12.2024";
        List<Long> STUDENTS_ID = new ArrayList<>(List.of(1L));
        groupRepository.create(new GroupEntity(1L, "group A"));
        studentRepository.create(new StudentEntity(1L,"Petrov","Vladimir","Iurevich", StudentStatus.ACTIVE,1L));
        studentRepository.create(new StudentEntity(2L,"Ivanov","Ivan","Iurevich", StudentStatus.ACTIVE,1L));
        lessonRepository.create(new LessonEntity(1L, 1L, LocalDate.of(2024, 12, 5), 1, 1L, groupId));
        attendanceRepository.create(new AttendanceEntity(1L,1L,STUDENTS_ID));
        teacherRepository.create(new TeacherEntity(1L,"Ashaev","Igor","Viktorovich"));
        LessonService lessonService = new LessonService(lessonRepository,studentRepository,groupRepository,teacherRepository,subjectRepository,attendanceRepository);
        EditLessonRequest request = new EditLessonRequest(1L,1L,date,3,1L,1L,STUDENTS_ID);
        lessonService.editLesson(request);
        assertEquals(3, lessonService.getLessonById(new IdRequest(1L)).number());
    }
    @Test
    void deleteLessonTest(){
        subjectRepository.create(new SubjectEntity(1L,"Math"));
        Long groupId = 1L;
        List<Long> STUDENTS_ID = new ArrayList<>(List.of(1L));
        groupRepository.create(new GroupEntity(1L, "group A"));
        studentRepository.create(new StudentEntity(1L,"Petrov","Vladimir","Iurevich", StudentStatus.ACTIVE,1L));
        studentRepository.create(new StudentEntity(2L,"Ivanov","Ivan","Iurevich", StudentStatus.ACTIVE,1L));
        lessonRepository.create(new LessonEntity(1L, 1L, LocalDate.of(2024, 12, 5), 1, 1L, groupId));
        attendanceRepository.create(new AttendanceEntity(1L,1L,STUDENTS_ID));
        teacherRepository.create(new TeacherEntity(1L,"Ashaev","Igor","Viktorovich"));
        LessonService lessonService = new LessonService(lessonRepository,studentRepository,groupRepository,teacherRepository,subjectRepository,attendanceRepository);
        IdRequest request = new IdRequest(1L);
        lessonService.deleteLesson(request);
        assertNull(lessonService.getLessonById(new IdRequest(1L)));
    }

    @Test
    void deleteLessonTest_notFound() throws Exception{
        LessonService lessonService = new LessonService(lessonRepository,studentRepository,groupRepository,teacherRepository,subjectRepository,attendanceRepository);
        IdRequest request = new IdRequest(1L);
        lessonService.deleteLesson(request);
    }


}



