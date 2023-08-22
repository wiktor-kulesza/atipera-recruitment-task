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

@RestController
@RequestMapping("/api/v1")
public class RepositoryController {

    private final RepositoryDetailsService repositoryDetailsService;

    public RepositoryController(RepositoryDetailsService repositoryDetailsService) {
        this.repositoryDetailsService = repositoryDetailsService;
    }

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
