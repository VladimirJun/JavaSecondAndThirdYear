package org.example.demo1.repository;

import org.example.demo1.entity.LessonEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LessonRepository extends CrudRepository<LessonEntity, Long> {
    Optional<LessonEntity> getLessonByTeacherId(Long teacherId);

    Optional<LessonEntity> getLessonByGroupId(Long groupId);

    @Query("SELECT l FROM LessonEntity l WHERE l.teacher.id = :teacherId AND l.date > :dateStart AND l.date < :dateEnd")
    List<LessonEntity> getLessonsByTeacherForPeriod(@Param("teacherId") Long teacherId,
                                              @Param("dateStart") LocalDate dateStart,
                                              @Param("dateEnd") LocalDate dateEnd);

    @Query("SELECT l FROM LessonEntity l WHERE l.group.id = :groupId AND l.date > :dateStart AND l.date < :dateEnd")
    List<LessonEntity> getLessonsByGroupForPeriod(@Param("groupId") Long groupId,
                                            @Param("dateStart") LocalDate dateStart,
                                            @Param("dateEnd") LocalDate dateEnd);

}