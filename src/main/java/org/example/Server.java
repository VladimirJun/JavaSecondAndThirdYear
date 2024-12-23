package org.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.example.controller.LessonController;
import org.example.controller.SubjectController;
import org.example.model.*;
import org.example.repository.HashMapStorage;
import org.example.request.*;
import org.example.response.AddResponse;
import org.example.response.CommonResponse;
import org.example.response.GetResponse;
import org.example.service.GroupService;
import org.example.service.StudentService;
import org.example.service.SubjectService;
import org.example.service.TeacherService;
import org.example.service.LessonService;
import org.example.controller.*;
import org.example.utilities.HTTPStatus;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import static java.util.Map.entry;

public class Server {

    private final HashMapStorage storage = new HashMapStorage();

    // Services
    private final GroupService groupService = new GroupService(storage.getGroupRepository());
    private final StudentService studentService = new StudentService(
            storage.getStudentRepository(),
            storage.getGroupRepository()
    );
    private final TeacherService teacherService = new TeacherService(storage.getTeacherRepository());
    private final SubjectService subjectService = new SubjectService(storage.getSubjectRepository());
    private final LessonService lessonService = new LessonService(
            storage.getLessonRepository(),
            storage.getStudentRepository(),
            storage.getGroupRepository(),
            storage.getTeacherRepository(),
            storage.getSubjectRepository(),
            storage.getAttendanceRepository()
    );

    // Controllers
    private final GroupController groupController = new GroupController(groupService);
    private final StudentController studentController = new StudentController(studentService);
    private final TeacherController teacherController = new TeacherController(teacherService);
    private final SubjectController subjectController = new SubjectController(subjectService);
    private final LessonController lessonController = new LessonController(lessonService);

    private final Map<String, Function<String, CommonResponse<?>>> endpoints = Map.ofEntries(
            entry(Endpoints.GET_GROUPS, s -> getGroups()),
            entry(Endpoints.GET_GROUP_BY_ID, this::getGroupById),
            entry(Endpoints.ADD_GROUP, this::addGroup),
            entry(Endpoints.EDIT_GROUP, this::editGroup),
            entry(Endpoints.DELETE_GROUP, this::deleteGroup),
            entry(Endpoints.GET_STUDENTS_BY_GROUP_ID, this::getStudentsByGroup),
            entry(Endpoints.GET_STUDENT_BY_ID, this::getStudentById),
            entry(Endpoints.ADD_STUDENT, this::addStudent),
            entry(Endpoints.EDIT_STUDENT, this::editStudent),
            entry(Endpoints.DELETE_STUDENT, this::deleteStudent),
            entry(Endpoints.GET_TEACHERS, s -> getTeachers()),
            entry(Endpoints.GET_TEACHER_BY_ID, this::getTeacherById),
            entry(Endpoints.ADD_TEACHER, this::addTeacher),
            entry(Endpoints.EDIT_TEACHER, this::editTeacher),
            entry(Endpoints.DELETE_TEACHER, this::deleteTeacher),
            entry(Endpoints.GET_SUBJECTS, s -> getSubjects()),
            entry(Endpoints.GET_SUBJECT_BY_ID, this::getSubjectById),
            entry(Endpoints.ADD_SUBJECT, this::addSubject),
            entry(Endpoints.EDIT_SUBJECT, this::editSubject),
            entry(Endpoints.DELETE_SUBJECT, this::deleteSubject),
            entry(Endpoints.GET_LESSONS_BY_GROUP_ID, this::getLessonsByGroup),
            entry(Endpoints.GET_LESSONS_BY_TUTOR_ID, this::getLessonsByTeacher),
            entry(Endpoints.GET_LESSONS_BY_ID, this::getLessonById),
            entry(Endpoints.ADD_LESSON, this::addLesson),
            entry(Endpoints.EDIT_LESSON, this::editLesson),
            entry(Endpoints.DELETE_LESSON, this::deleteLesson)
    );

    // Jackson ObjectMapper
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public CommonResponse<?> execute(String path, String body) {
        try {
            final Function<String, CommonResponse<?>> endpoint = endpoints.get(path);
            if (endpoint == null) {
                return new CommonResponse<>(HTTPStatus.NOT_FOUND, "No such endpoint exists");
            }
            return endpoint.apply(body);
        } catch (Exception e) {
            return new CommonResponse<>(HTTPStatus.INTERNAL_SERVER_ERROR, "Server error: " + e.getMessage());
        }
    }

    /* Group Endpoints */
    private CommonResponse<GetResponse<Collection<GroupModel>>> getGroups() {
        return groupController.getAllGroups();
    }

    private CommonResponse<GetResponse<GroupModel>> getGroupById(String body) {
        return parseAndExecute(body, IdRequest.class, groupController::getGroupById);
    }

    private CommonResponse<AddResponse> addGroup(String body) {
        return parseAndExecute(body, AddGroupRequest.class, groupController::addGroup);
    }

    private CommonResponse<Void> editGroup(String body) {
        return parseAndExecute(body, EditGroupRequest.class, groupController::editGroup);
    }

    private CommonResponse<Void> deleteGroup(String body) {
        return parseAndExecute(body, IdRequest.class, groupController::deleteGroup);
    }

    /* Student Endpoints */
    private CommonResponse<GetResponse<Collection<StudentModel>>> getStudentsByGroup(String body) {
        return parseAndExecute(body, IdRequest.class, studentController::getStudentsByGroup);
    }

    private CommonResponse<GetResponse<StudentModel>> getStudentById(String body) {
        return parseAndExecute(body, IdRequest.class, studentController::getStudentById);
    }

    private CommonResponse<AddResponse> addStudent(String body) {
        return parseAndExecute(body, AddStudentRequest.class, studentController::addStudent);
    }

    private CommonResponse<Void> editStudent(String body) {
        return parseAndExecute(body, EditStudentRequest.class, studentController::editStudent);
    }

    private CommonResponse<Void> deleteStudent(String body) {
        return parseAndExecute(body, IdRequest.class, studentController::deleteStudent);
    }

    /* Teacher Endpoints */
    private CommonResponse<GetResponse<Collection<TeacherModel>>> getTeachers() {
        return teacherController.getTeachers();
    }

    private CommonResponse<GetResponse<TeacherModel>> getTeacherById(String body) {
        return parseAndExecute(body, IdRequest.class, teacherController::getTeacherById);
    }

    private CommonResponse<AddResponse> addTeacher(String body) {
        return parseAndExecute(body, AddTeacherRequest.class, teacherController::addTeacher);
    }

    private CommonResponse<Void> editTeacher(String body) {
        return parseAndExecute(body, EditTeacherRequest.class, teacherController::editTeacher);
    }

    private CommonResponse<Void> deleteTeacher(String body) {
        return parseAndExecute(body, IdRequest.class, teacherController::deleteTeacher);
    }

    /* Subject Endpoints */
    private CommonResponse<GetResponse<Collection<SubjectModel>>> getSubjects() {
        return subjectController.getSubjects();
    }

    private CommonResponse<GetResponse<SubjectModel>> getSubjectById(String body) {
        return parseAndExecute(body, IdRequest.class, subjectController::getSubjectById);
    }

    private CommonResponse<AddResponse> addSubject(String body) {
        return parseAndExecute(body, AddSubjectRequest.class, subjectController::addSubject);
    }

    private CommonResponse<Void> editSubject(String body) {
        return parseAndExecute(body, EditSubjectRequest.class, subjectController::editSubject);
    }

    private CommonResponse<Void> deleteSubject(String body) {
        return parseAndExecute(body, IdRequest.class, subjectController::deleteSubject);
    }

    /* Lesson Endpoints */
    private CommonResponse<GetResponse<Collection<LessonModel>>> getLessonsByGroup(String body) {
        return parseAndExecute(body, GetLessonsByGroupRequest.class, lessonController::getLessonsByGroup);
    }

    private CommonResponse<GetResponse<Collection<LessonModel>>> getLessonsByTeacher(String body) {
        return parseAndExecute(body, GetLessonsByTeacherRequest.class, lessonController::getLessonsByTeacher);
    }

    private CommonResponse<GetResponse<LessonModel>> getLessonById(String body) {
        return parseAndExecute(body, IdRequest.class, lessonController::getLessonById);
    }

    private CommonResponse<AddResponse> addLesson(String body) {
        return parseAndExecute(body, AddLessonRequest.class, lessonController::addLesson);
    }

    private CommonResponse<Void> editLesson(String body) {
        return parseAndExecute(body, EditLessonRequest.class, lessonController::editLesson);
    }

    private CommonResponse<Void> deleteLesson(String body) {
        return parseAndExecute(body, IdRequest.class, lessonController::deleteLesson);
    }

    private <T, R> CommonResponse<R> parseAndExecute(String body, Class<T> clazz, Function<T, CommonResponse<R>> handler) {
        try {
            T request = OBJECT_MAPPER.readValue(body, clazz);
            return handler.apply(request);
        } catch (Exception e) {
            return new CommonResponse<>(HTTPStatus.BAD_REQUEST, "Invalid JSON format: " + e.getMessage());
        }
    }
}
//метод main запросы в консоли