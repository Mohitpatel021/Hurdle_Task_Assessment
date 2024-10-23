import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CourseService } from '../course.service';
import { HttpClientModule } from '@angular/common/http';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterModule, HttpClientModule, FormsModule, ReactiveFormsModule],
  providers: [CourseService],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  courses: any[] = [];
  errorMessage: string = '';
  successMessage: string = '';
  selectedCourseId: number | null = null;
  updateCourseId: number | null = null;
  selectedCourseIds: number[] = [];
  updateUserForm: FormGroup;
  addCourse: FormGroup;
  constructor(private courseService: CourseService, private fb: FormBuilder) {
    this.updateUserForm = this.fb.group({
      courseName: [''],
      mentorName: [''],
      authorName: [''],
      description: ['']
    });
    this.addCourse = this.fb.group({
      title: ['', Validators.required],
      mentor: ['', Validators.required],
      author: ['', Validators.required],
      des: ['', Validators.required]
    });
  }


  ngOnInit(): void {
    this.loadCourses();
  }

  loadCourses(): void {
    this.courseService.getAllCourses().subscribe(
      (response) => {
        this.courses = response.content;
      },
      (error) => {
        this.errorMessage = 'Error loading courses: ' + error;
      }
    );
  }

  // Create Course
  createCourse(): void {
    const updateData = {
      title: this.addCourse.get('title')?.value,
      mentor: this.addCourse.get('mentor')?.value,
      writer: this.addCourse.get('author')?.value,
      description: this.addCourse.get('des')?.value,
    };

    this.courseService.createCourse(updateData).subscribe(
      (response) => {
        this.closeaddModal();
        this.addCourse.reset();
        this.successMessage = 'Course created successfully!';
        this.loadCourses();

      },
      (error) => {
        this.errorMessage = 'Error creating course: ' + error;
      }
    );
  }



  // Delete single course
  confirmDelete(): void {
    if (this.selectedCourseId !== null) {
      this.courseService.deleteCourse(this.selectedCourseId).subscribe(
        (response) => {
          this.successMessage = 'Course deleted successfully!';
          this.loadCourses();
          this.closeModal();
        },
        (error) => {
          this.errorMessage = 'Error deleting course: ' + error;
          this.closeModal();
        }
      );
    }
  }

  // Delete multiple courses
  deleteSelectedCourses(): void {
    this.courseService.deleteCoursesByIds(this.selectedCourseIds).subscribe(
      (response) => {
        this.successMessage = 'Selected courses deleted successfully!';
        this.loadCourses();
      },
      (error) => {
        this.errorMessage = 'Error deleting selected courses: ' + error;
      }
    );
  }

  // Modal management
  openDeleteModal(courseId: number): void {
    this.selectedCourseId = courseId;
    const modalElement = document.getElementById('hs-scale-animation-modal');
    if (modalElement) {
      modalElement.classList.remove('hidden');
      modalElement.classList.add('flex');
    }
  }

  closeModal(): void {
    const modalElement = document.getElementById('hs-scale-animation-modal');
    if (modalElement) {
      modalElement.classList.add('hidden');
      modalElement.classList.remove('flex');
    }
  }
  openUpdateModal(courseId: number): void {
    this.updateCourseId = courseId;
    const modalElement = document.getElementById('subscribe-form-modal');
    if (modalElement) {
      modalElement.classList.remove('hidden');
      modalElement.classList.add('flex');
    }
  }

  // Update the course details
  updateCourse(): void {
    if (this.updateCourseId !== null) {
      const updateData = {

        courseName: this.updateUserForm.get('courseName')?.value,
        mentorName: this.updateUserForm.get('mentorName')?.value,
        authorName: this.updateUserForm.get('authorName')?.value,
        description: this.updateUserForm.get('description')?.value,
      };
      this.courseService.updateCourse(this.updateCourseId, updateData).subscribe(
        (response) => {
          this.successMessage = 'Course updated successfully!';
          this.loadCourses();
          this.updateUserForm.reset();
          this.closeUpdateModal();
        },
        (error) => {
          this.errorMessage = 'Error updating course: ' + error;
        }
      );
    }
  }

  // Close the update modal
  closeUpdateModal(): void {
    const modalElement = document.getElementById('subscribe-form-modal');
    if (modalElement) {
      modalElement.classList.add('hidden');
      modalElement.classList.remove('flex');
    }
  }

  openaddModal(): void {
    const modalElement = document.getElementById('register-form-modal');
    if (modalElement) {
      modalElement.classList.remove('hidden');
      modalElement.classList.add('flex');
    }
  }
  closeaddModal(): void {
    const modalElement = document.getElementById('register-form-modal');
    if (modalElement) {
      modalElement.classList.add('hidden');
      modalElement.classList.remove('flex');
    }
  }
}


