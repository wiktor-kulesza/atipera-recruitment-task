package com.example.atipera.exception;

/**
 * Custom exception class for representing exceptions related to GitHub API operations.
 * This exception class extends the RuntimeException class.
 *
 * @since 22-08-2023
 * @author Wiktor Kulesza
 */
public class GithubApiException extends RuntimeException {

    public GithubApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public GithubApiException(String message) {
        super(message);
    }
}
