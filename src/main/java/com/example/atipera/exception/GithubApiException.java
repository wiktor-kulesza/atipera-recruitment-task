package com.example.atipera.exception;

public class GithubApiException extends RuntimeException {

    public GithubApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public GithubApiException(String message) {
        super(message);
    }
}
