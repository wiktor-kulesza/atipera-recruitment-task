package com.example.atipera.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Class representing an error response.
 *
 * @since 22-08-2023
 * @author Wiktor Kulesza
 */
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private Integer status;
    private String message;
}
