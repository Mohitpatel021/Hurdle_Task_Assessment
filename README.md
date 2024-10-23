# Course Management Dashboard

A full-stack web application for managing courses, built using Angular for the frontend and Spring Boot for the backend. It allows users to create, read, update, and delete course data, with additional functionalities like searching and filtering courses.

## Table of Contents
- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies](#technologies)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Acknowledgments](#acknowledgments)

## Project Overview
This project is a course management system with a user-friendly dashboard that allows administrators to manage courses. The frontend is developed using Angular, and the backend uses Spring Boot to provide a RESTful API for course operations.

## Features
- Create a new course
- View all courses with pagination and search functionality
- Update course details
- Delete single or multiple courses

## Technologies
- **Frontend**: Angular 17, HTML, CSS, TypeScript
- **Backend**: Spring Boot, Java
- **Database**: MySQL (or any relational database)
- **Tools**: Postman (for API testing), VS Code

## Installation
### Prerequisites
- Node.js and npm
- Java 11+ and Maven
- MySQL (or any relational database)
- Angular CLI

### Backend Setup (Spring Boot)
1. Clone the repository: `git clone <your-backend-repo-url>`
2. Navigate to the backend project directory: `cd backend`
3. Configure the database in `application.properties`:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/course_db
    spring.datasource.username=<your-username>
    spring.datasource.password=<your-password>
    ```
4. Run the backend: `mvn spring-boot:run`

### Frontend Setup (Angular)
1. Clone the repository: `git clone <your-frontend-repo-url>`
2. Navigate to the frontend project directory: `cd frontend`
3. Install dependencies: `npm install`
4. Run the Angular project: `ng serve`

## Usage
1. Open your browser and navigate to `http://localhost:4200` to access the Angular frontend.
2. Use the dashboard to manage courses:
   - Create a new course
   - Update existing courses
   - Delete single or multiple courses

## API Endpoints

### Create a Course
- `POST /create`
  - Request Body:
    ```json
    {
      "courseName": "Course1",
      "authorName": "Author1",
      "mentorName": "Mentor1",
      "description": "This is a course.",
      "courseDuration": "SIX_MONTH"
    }
    ```

### Get All Courses
- `GET /get/all?page=<pageNumber>&size=<pageSize>`

### Update a Course
- `PUT /update/{id}`
  - Request Body:
    ```json
    {
      "courseName": "Updated Course",
      "authorName": "Updated Author",
      "mentorName": "Updated Mentor",
      "description": "Updated Description",
      "courseDuration": "TWELVE_MONTH"
    }
    ```

### Delete a Course
- `DELETE /delete/{id}`

### Delete Multiple Courses
- `DELETE /delete/all`
  - Request Body:
    ```json
    [1, 2, 3]
    ```

## Acknowledgments
- Thanks to the Angular and Spring Boot communities for their extensive documentation and resources.
- Special thanks to all contributors who helped in making this project a success.
 
