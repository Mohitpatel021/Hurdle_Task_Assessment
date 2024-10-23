package com.assessment.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.task.entity.CourseDTO;
import com.assessment.task.entity.CourseUpdateDto;
import com.assessment.task.entity.CoursesEntity;
import com.assessment.task.exception.ResourceNotFoundException;
import com.assessment.task.service.CourseServiceImpl;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CourseController {

	@Autowired
	private CourseServiceImpl courseService;

	// Create course
	@PostMapping("/create")
	public ResponseEntity<Map<String, Object>> createCourse( @RequestBody CourseDTO courseRequest) {

		CoursesEntity savedCourse = courseService.saveCourse(courseRequest);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Course created successfully");
		response.put("data", savedCourse);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// Get course by ID
	@GetMapping("/get/{id}")
	public ResponseEntity<Map<String, Object>> getCourseById(@PathVariable Long id) {
		CoursesEntity course = courseService.getCourseById(id);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Course retrieved successfully");
		response.put("data", course);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Delete course
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Map<String, Object>> deleteCourse(@PathVariable Long id) {
		courseService.deleteCourse(id);
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Course deleted successfully");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCourse(@PathVariable Long id, @RequestBody CourseUpdateDto courseUpdateDto) {
		try {
			CoursesEntity updatedCourse = courseService.updateCourse(id, courseUpdateDto);
			return ResponseEntity.ok(updatedCourse);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(404).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body("An unexpected error occurred");
		}
	}

	@GetMapping("/get/all")
	public ResponseEntity<?> getAllCourses(@RequestParam(name = "searchParam", required = false) String searchParam,
			@RequestParam(name = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(name = "PageSize", defaultValue = "10", required = false) int pageSize) {
		try {
			Pageable page = PageRequest.of(pageNumber, pageSize);
			Page<CoursesEntity> courses = courseService.getAllCourses(searchParam, page);
			return ResponseEntity.ok(courses);
		} catch (Exception e) {
			return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
		}
	}

	@DeleteMapping("/delete/all")
	public ResponseEntity<?> deleteCoursesByIds(@RequestBody List<Long> courseIds) {
		try {
			courseService.deleteCoursesByIds(courseIds);
			return ResponseEntity.ok("Courses with the provided IDs have been deleted successfully.");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An unexpected error occurred: " + e.getMessage());
		}
	}
}
