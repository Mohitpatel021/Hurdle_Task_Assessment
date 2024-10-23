package com.assessment.task.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "course_details")
@EntityListeners(AuditingEntityListener.class)
public class CoursesEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long courseId;
	@Column(name = "courseName")
	private String courseName;
	@Enumerated(EnumType.STRING)
	@Column(name = "courseDuration")
	private CourseDuration courseDuration;
	@Column(name = "mentorName" )
	private String mentorName;
	@Column(name = "authorName")
	private String authorName;
	@Column(name = "description")
	private String description;
	@CreatedDate
	@Column(name = "createdAt")
	private LocalDateTime createAt;
	@LastModifiedDate
	@Column(name = "updatedAt")
	private LocalDateTime updatedAt;

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public CourseDuration getCourseDuration() {
		return courseDuration;
	}

	public void setCourseDuration(CourseDuration courseDuration) {
		this.courseDuration = courseDuration;
	}

	public String getMentorName() {
		return mentorName;
	}

	public void setMentorName(String mentorName) {
		this.mentorName = mentorName;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
