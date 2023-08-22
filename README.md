# GitHub Repository API Consumer

This is a small Java project for recruitment process that serves as an API consumer for GitHub repositories. The project allows you to retrieve information about a user's GitHub repositories, including repository details and branch information.

## Contact Information
### Author: 
<b>Wiktor Kulesza </b>
### Email: 
<b>wiktor.kulesza037@gmail.com </b>
## Project Overview

The project is built using Java 17 and Spring 3, with Maven as the build tool. It provides a RESTful API that allows you to query GitHub repositories for a given username. The API returns information about repositories that are not forks, including repository name, owner login, and branch details.

## API Endpoints

### Get User Repositories Details

Endpoint: `/api/v1/repos/{username}`

**Request:**
- Method: GET
- Headers:
    - Accept: application/json (required)

**Response:**
- Returns a list of repositories for the given username, along with branches details.

**Error Handling:**
- If the username is not found, a 404 response is returned with an appropriate error message.
- If the `Accept` header is set to `application/xml`, a 406 response is returned with an error message indicating unacceptable format.
- Other internal errors result in a 500 response with error details.

## Running the Project

### Using Java
To run the project, follow these steps:

1. In case of accessing project from VCS - Clone the repository to your local machine.
2. Ensure you have Java 17 and Maven installed.
3. Navigate to the project directory and run the following command to build the project:

   ```bash
   mvn clean install
   ```

4. After the build is successful, run the project using:

   ```bash
   java -jar target/atipera-0.0.1-SNAPSHOT.jar
   ```

### Using Docker

To run the project using Docker, follow these steps:

1. Ensure you have docker installed
2. Pull image from registry by running a command:

   ```bash
   docker pull gimli037/atipera-app:v1
   ```
3. Run the image using:

   ```bash
   docker run -p 8080:8080 -d gimli037/atipera-app:v1
   ```

## API Usage

To use the API, make a GET request to the following endpoint:

```
GET http://localhost:8080/api/v1/repos/{username}
```

Replace `{username}` with the GitHub username you want to retrieve repository details for.

The response will include information about the repositories and their branches, as specified in the task.

## Error Handling

The API handles various error scenarios, including:

- Username not found: Returns a 404 response with an appropriate error message.
- Unacceptable `Accept` header: Returns a 406 response with an error message.
- Internal server errors: Returns a 500 response with error details.

## Additional Information

- This project demonstrates the use of Java 17, Spring 3, and Maven.
- The `RepositoryController` class handles API requests and error handling.
- The `GithubApiService` class communicates with the GitHub API to fetch repository details.
- The `ErrorResponse` class represents the structure of error responses.
