package com.example.atipera.service;

import com.example.atipera.constant.ResponseConstant;
import com.example.atipera.exception.GithubApiException;
import com.example.atipera.model.UserRepositoryDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryDetailsService {

    private final GithubApiService githubApiService;

    public RepositoryDetailsService(GithubApiService githubApiService) {
        this.githubApiService = githubApiService;
    }

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
