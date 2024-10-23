package com.assessment.task.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.assessment.task.entity.CourseDTO;
import com.assessment.task.entity.CourseDuration;
import com.assessment.task.entity.CourseUpdateDto;
import com.assessment.task.entity.CoursesEntity;
import com.assessment.task.exception.InternalServerErrorException;
import com.assessment.task.exception.InvalidRequestException;
import com.assessment.task.exception.ResourceNotFoundException;
import com.assessment.task.repository.CourseRepository;

@Service
public class CourseServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired
	private CourseRepository courseRepository;

	/**
	 * Saves a new course or updates an existing course in the database.
	 *
	 * @param courseRequest DTO containing course data.
	 * @return Saved or updated course entity.
	 */
	@Transactional
	public CoursesEntity saveCourse(CourseDTO courseRequest) {

		CoursesEntity course = new CoursesEntity();
		course.setCourseName(courseRequest.getTitle());
		course.setCourseDuration(CourseDuration.TWELVE_MONTH);
		course.setDescription(courseRequest.getDescription());
		course.setCourseDuration(courseRequest.getDuration());
		course.setAuthorName(courseRequest.getWriter());
		course.setMentorName(courseRequest.getMentor());

		try {
			CoursesEntity savedCourse = courseRepository.save(course);
			logger.info("Course saved successfully with ID: {}", savedCourse.getCourseId());
			return savedCourse;
		} catch (Exception e) {
			logger.error("Error occurred while saving the course", e);
			throw new InternalServerErrorException("Failed to save the course. Please try again.");
		}
	}

	/**
	 * Retrieves a course by its ID.
	 *
	 * @param id The course ID to look up.
	 * @return The found course entity.
	 * @throws ResourceNotFoundException if the course is not found.
	 */
	@Transactional(readOnly = true)
	public CoursesEntity getCourseById(Long id) {

		try {
			return courseRepository.findByCourseId(id)
					.orElseThrow(() -> new ResourceNotFoundException("Course not found with ID: " + id));
		} catch (Exception e) {
			logger.error("Error occurred while retrieving course with ID: {}", id, e);
			throw new InternalServerErrorException("Failed to retrieve course. Please try again.");
		}
	}

	/**
	 * Deletes a course by its ID.
	 *
	 * @param id The course ID to delete.
	 * @throws ResourceNotFoundException if the course is not found.
	 */
	@Transactional
	public void deleteCourse(Long id) {

		try {
			CoursesEntity course = getCourseById(id); // This will throw an exception if course is not found
			courseRepository.delete(course);
			logger.info("Course with ID {} deleted successfully", id);
		} catch (Exception e) {
			logger.error("Error occurred while deleting course with ID: {}", id, e);
			throw new InternalServerErrorException("Failed to delete course. Please try again.");
		}
	}

	/**
	 * Validates the course request to ensure required fields are present.
	 *
	 * @param courseRequest the course DTO.
	 */
	private void validateCourseRequest(CourseDTO courseRequest) {
		if (courseRequest == null) {
			throw new IllegalArgumentException("Course request cannot be null");
		}

		if (!StringUtils.hasText(courseRequest.getTitle())) {
			throw new IllegalArgumentException("Course title must not be empty");
		}

		if (!StringUtils.hasText(courseRequest.getDescription())) {
			throw new IllegalArgumentException("Course description must not be empty");
		}

	}

	@Transactional
	public CoursesEntity updateCourse(Long id, CourseUpdateDto courseUpdateDto) {
		Optional<CoursesEntity> optionalCourse = courseRepository.findById(id);
		if (optionalCourse.isEmpty()) {
			throw new ResourceNotFoundException("Course not found with id " + id);
		}

		CoursesEntity existingCourse = optionalCourse.get();

		// Only update fields that are present in the DTO
		if (courseUpdateDto.getCourseName() != null && !courseUpdateDto.getCourseName().isEmpty()) {
			existingCourse.setCourseName(courseUpdateDto.getCourseName());
		}
		if (courseUpdateDto.getAuthorName() != null && !courseUpdateDto.getAuthorName().isEmpty()) {
			existingCourse.setAuthorName(courseUpdateDto.getAuthorName());
		}

		if (courseUpdateDto.getDescription() != null && !courseUpdateDto.getDescription().isEmpty()) {
			existingCourse.setDescription(courseUpdateDto.getDescription());
		}
		if (courseUpdateDto.getMentorName() != null && !courseUpdateDto.getMentorName().isEmpty()) {
			existingCourse.setMentorName(courseUpdateDto.getMentorName());
		}

		existingCourse.setUpdatedAt(LocalDateTime.now());
		return courseRepository.save(existingCourse);
	}

	@Transactional(readOnly = true)
	public Page<CoursesEntity> getAllCourses(String search, Pageable pageable) {
		try {
			if (search != null && !search.isEmpty()) {
				return courseRepository.searchCourses(search, pageable);
			} else {
				return courseRepository.findAll(pageable);
			}
		} catch (Exception e) {
			throw new InvalidRequestException("Invalid search parameters or request");
		}
	}

	@Transactional
	public void deleteCoursesByIds(List<Long> courseIds) {
		for (Long courseId : courseIds) {
			CoursesEntity course = courseRepository.findById(courseId).orElseThrow(
					() -> new ResourceNotFoundException("Course with ID " + courseId + " does not exist."));
			courseRepository.delete(course);
		}
	}
}
