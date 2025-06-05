CREATE TABLE t_teachers
(
    c_id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    c_name       VARCHAR(15) NOT NULL,
    c_surname    VARCHAR(25) NOT NULL,
    c_patronymic VARCHAR(40)
);

CREATE TABLE t_groups
(
    c_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    c_title VARCHAR(15) NOT NULL
);

CREATE TABLE t_students
(
    c_id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    c_name               VARCHAR(15) NOT NULL,

    c_surname            VARCHAR(25) NOT NULL,
    c_patronymic         VARCHAR(40),
    c_status_of_students VARCHAR(30) NOT NULL,
    c_group_id           BIGINT NOT NULL,
    CONSTRAINT chk_student_status CHECK (c_status_of_students IN ('ACTIVE', 'NON_ACTIVE')),
    CONSTRAINT fk_student_group FOREIGN KEY (c_group_id) REFERENCES t_groups (c_id)
);

CREATE TABLE t_subjects
(
    c_id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    c_title VARCHAR(35) NOT NULL
);

CREATE TABLE t_lessons
(
    c_id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    c_date          DATE,
    c_number_lesson INT,
    c_group_id      BIGINT NOT NULL,
    c_teacher_id    BIGINT NOT NULL,
    CONSTRAINT fk_lesson_group FOREIGN KEY (c_group_id) REFERENCES t_groups (c_id),
    CONSTRAINT fk_lesson_teacher FOREIGN KEY (c_teacher_id) REFERENCES t_teachers (c_id)
);

CREATE TABLE t_lesson_attendance
(
    c_id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    c_attendance BOOLEAN,
    c_lesson_id  BIGINT NOT NULL,
    c_student_id BIGINT NOT NULL,
    CONSTRAINT fk_lesson_attendance_lesson FOREIGN KEY (c_lesson_id) REFERENCES t_lessons (c_id),
    CONSTRAINT fk_lesson_attendance_student FOREIGN KEY (c_student_id) REFERENCES t_students (c_id)
);

CREATE TABLE t_users
(
    c_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    c_username VARCHAR(50) NOT NULL UNIQUE,
    c_password VARCHAR(255) NOT NULL,
    c_role VARCHAR(30) NOT NULL,
    c_profile_id BIGINT,
    CONSTRAINT chk_user_role CHECK (c_role IN ('ROLE_ADMIN', 'ROLE_TEACHER', 'ROLE_STUDENT'))
);
