package com.assessment.task.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CourseDTO {

	@NotBlank(message = "Title Should be Present")
	private String title;
	@NotBlank(message = "Description Should be Present")
	private String description;
	@NotNull(message = "Duration Should be Present")
	private CourseDuration duration;
	private String writer;
	private String mentor;


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CourseDuration getDuration() {
		return duration;
	}

	public void setDuration(CourseDuration duration) {
		this.duration = duration;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getMentor() {
		return mentor;
	}

	public void setMentor(String mentor) {
		this.mentor = mentor;
	}

	public static CoursesEntity entityMapper(CourseDTO dto) {
		CoursesEntity entity = new CoursesEntity();
		entity.setCourseName(dto.getTitle());
		entity.setAuthorName(dto.getWriter());
		entity.setCourseDuration(dto.getDuration());
		entity.setDescription(dto.getDescription());
		entity.setMentorName(dto.getMentor());
		return entity;
	}

	public static String dateFormatter(LocalDateTime now) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return now.format(formatter);
	}
}
