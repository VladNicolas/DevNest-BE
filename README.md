# DevNest - Backend API

Welcome to the backend API documentation for **DevNest**. This API provides endpoints to manage user authentication, user profiles, projects, comments, issues, messages, and invitations.

## Getting Started

To use this API, you need to have an authentication token. You can obtain a token by signing up or logging in using the provided authentication endpoints.

## Authentication Endpoints

### Signup

- **POST /auth/signup**: Creates a new user account.

### Login

- **POST /auth/login**: Logs in an existing user.

## User Endpoints

### User Profile

- **GET /api/users/profile**: Retrieves the user's profile information.

## Project Endpoints

### Get Projects

- **GET /api/projects**: Retrieves a list of projects.

### Get Project by ID

- **GET /api/projects/{projectId}**: Retrieves a project by its ID.

### Create Project

- **POST /api/projects**: Creates a new project.

### Update Project

- **PATCH /api/projects/{projectId}**: Updates an existing project.

### Delete Project

- **DELETE /api/projects/{projectId}**: Deletes a project.

## Comment Endpoints

### Create Comment

- **POST /api/comments**: Creates a new comment.

### Delete Comment

- **DELETE /api/comments/{commentId}**: Deletes a comment.

## Issue Endpoints

### Get Issue by ID

- **GET /api/issues/{issueId}**: Retrieves an issue by its ID.

### Get Issues by Project ID

- **GET /api/issues/project/{projectId}**: Retrieves issues by project ID.

