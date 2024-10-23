package com.assessment.task.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.task.entity.CoursesEntity;

@Repository
public interface CourseRepository extends JpaRepository<CoursesEntity, Long> {

	Optional<CoursesEntity> findByCourseId(Long id);

	@Query("SELECT c FROM CoursesEntity c " + "WHERE LOWER(c.courseName) LIKE LOWER(CONCAT('%', :search, '%')) "
			+ "OR LOWER(c.authorName) LIKE LOWER(CONCAT('%', :search, '%'))"
			+ "OR LOWER(c.description) LIKE LOWER(CONCAT('%', :search, '%'))")
	Page<CoursesEntity> searchCourses(@Param("search") String search, Pageable pageable);

}
