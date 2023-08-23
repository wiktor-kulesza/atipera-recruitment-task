package com.example.atipera.controller;

import com.example.atipera.constant.ResponseConstant;
import com.example.atipera.exception.GithubApiException;
import com.example.atipera.model.ErrorResponse;
import com.example.atipera.service.RepositoryDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Controller class for handling repository-related API endpoints.
 *
 * @since 22-08-2023
 * @author Wiktor Kulesza
 */
@RestController
@RequestMapping("/api/v1")
public class RepositoryController {

    private final RepositoryDetailsService repositoryDetailsService;

    /**
     * Constructor for RepositoryController.
     *
     * @param repositoryDetailsService Service responsible for retrieving repository details.
     */
    public RepositoryController(RepositoryDetailsService repositoryDetailsService) {
        this.repositoryDetailsService = repositoryDetailsService;
    }

    /**
     * Retrieves details of repositories belonging to a user.
     *
     * @param acceptHeader Header specifying the accepted response format (e.g., JSON, XML).
     * @param username     The username of the user whose repositories details are to be retrieved.
     * @return ResponseEntity containing repository details or an appropriate error response.
     */
    @GetMapping(path = "/repos/{username}")
    public ResponseEntity<?> getUserRepositoriesDetails(@RequestHeader(value = "Accept") String acceptHeader, @PathVariable String username) {
        try {
            if (acceptHeader.equals(MediaType.APPLICATION_XML_VALUE)) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), ResponseConstant.UNACCEPTABLE_FORMAT));
            }
            return ResponseEntity.ok(repositoryDetailsService.getUserRepositoriesDetails(username));
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), ResponseConstant.USER_NOT_FOUND));
        } catch (GithubApiException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
        }
    }
}
