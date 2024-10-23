import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  private apiUrl = 'http://localhost:8082';

  constructor(private http: HttpClient) { }

  // Create a new course
  createCourse(course: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/create`, course).pipe(catchError(this.handleError));
  }

  // Get course by ID
  getCourseById(id: number): Observable<any> {
    return this.http.get(`${this.apiUrl}/get/${id}`).pipe(catchError(this.handleError));
  }

  // Get all courses with optional search
  getAllCourses(search: string = '', page: number = 0, size: number = 7): Observable<any> {
    if (search === null || search === '') {
      return this.http
        .get(`${this.apiUrl}/get/all?page=${page}&size=${size}`)
        .pipe(catchError(this.handleError));
    }
    return this.http
      .get(`${this.apiUrl}/get/all?searchParam=${search}&pageNumber=${page}&PageSize=${size}`)
      .pipe(catchError(this.handleError));
  }

  // Update course
  updateCourse(id: number, courseUpdate: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/update/${id}`, courseUpdate).pipe(catchError(this.handleError));
  }

  // Delete course by ID
  deleteCourse(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${id}`).pipe(catchError(this.handleError));
  }

  // Delete multiple courses by IDs
  deleteCoursesByIds(courseIds: number[]): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/all`, { body: courseIds }).pipe(catchError(this.handleError));
  }

  // Handle errors
  private handleError(error: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (error.error instanceof ErrorEvent) {
      // Client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // Server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(errorMessage);
  }
}
