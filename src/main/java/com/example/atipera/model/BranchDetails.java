package com.example.atipera.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Class representing details of a branch as name and last commit sha.
 *
 * @since 22-08-2023
 * @author Wiktor Kulesza
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class BranchDetails {

    private String name;
    private String lastCommitSha;
}
