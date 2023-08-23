package com.example.atipera.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

/**
 * Class representing details of a user's repository as name owner username and branches details.
 *
 * @since 22-08-2023
 * @author Wiktor Kulesza
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRepositoryDetails {

    private String name;
    private String login;
    private List<BranchDetails> branches;
}
