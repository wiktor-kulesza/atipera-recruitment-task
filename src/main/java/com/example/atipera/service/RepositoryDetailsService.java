package com.example.atipera.service;

import com.example.atipera.constant.ResponseConstant;
import com.example.atipera.exception.GithubApiException;
import com.example.atipera.model.UserRepositoryDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for retrieving user repository details.
 * This class uses the GithubApiService to fetch repository and branch details.
 *
 * @since 22-08-2023
 * @author Wiktor Kulesza
 */
@Service
public class RepositoryDetailsService {

    private final GithubApiService githubApiService;

    /**
     * Constructor for RepositoryDetailsService.
     *
     * @param githubApiService An instance of GithubApiService for interacting with the GitHub API.
     */
    public RepositoryDetailsService(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

    /**
     * Retrieves user's repository details along with branch details.
     *
     * @param username The GitHub username.
     * @return List of UserRepositoryDetails with associated branch details.
     * @throws GithubApiException if there's an issue with the GitHub API.
     */
    public List<UserRepositoryDetails> getUserRepositoriesDetails(String username) {
        try {
            List<UserRepositoryDetails> repositories = githubApiService.getRepositoriesDetails(username);
            repositories.forEach(repository -> {
                repository.setBranches(githubApiService.getBranchesDetails(repository));
            });
            return repositories;
        } catch (JsonProcessingException e) {
            throw new GithubApiException(ResponseConstant.RESPONSE_PARSING_ERROR, e);
        }
    }
}
