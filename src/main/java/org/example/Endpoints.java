package org.example;

public class Endpoints {

    /* Student Groups */
    public static final String GET_GROUPS = "/getGroups";
    public static final String GET_GROUP_BY_ID = "/getGroupById";
    public static final String ADD_GROUP = "/addGroup";
    public static final String EDIT_GROUP = "/editGroup";
    public static final String DELETE_GROUP = "/deleteGroup";

    /* Students */
    public static final String GET_STUDENTS_BY_GROUP_ID = "/getStudentsByGroupId";
    public static final String GET_STUDENT_BY_ID = "/getStudentById";
    public static final String ADD_STUDENT = "/addStudent";
    public static final String EDIT_STUDENT = "/editStudent";
    public static final String DELETE_STUDENT = "/deleteStudent";

    /* Teachers */
    public static final String GET_TEACHERS = "/getTeachers";
    public static final String GET_TEACHER_BY_ID = "/getTeachersById";
    public static final String ADD_TEACHER = "/addTeacher";
    public static final String EDIT_TEACHER = "/editTeacher";
    public static final String DELETE_TEACHER = "/deleteTeacher";

    /* Subjects */
    public static final String GET_SUBJECTS = "/getSubjects";
    public static final String GET_SUBJECT_BY_ID = "/getSubjectById";
    public static final String ADD_SUBJECT = "/addSubject";
    public static final String EDIT_SUBJECT = "/editSubject";
    public static final String DELETE_SUBJECT = "/deleteSubject";

    /* Lessons */
    public static final String GET_LESSONS_BY_GROUP_ID = "/getLessonsByGroupId";
    public static final String GET_LESSONS_BY_TUTOR_ID = "/getLessonsByTutorId";
    public static final String GET_LESSONS_BY_ID = "/getLessonsById";
    public static final String ADD_LESSON = "/addLesson";
    public static final String EDIT_LESSON = "/editLesson";
    public static final String DELETE_LESSON = "/deleteLesson";
}