package com.example.atipera.service;

import com.example.atipera.constant.ApiConstant;
import com.example.atipera.constant.ResponseConstant;
import com.example.atipera.exception.GithubApiException;
import com.example.atipera.model.BranchDetails;
import com.example.atipera.model.BranchDetailsDeserializer;
import com.example.atipera.model.UserRepositoryDetails;
import com.example.atipera.model.UserRepositoryDetailsDeserializer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Service class for interacting with the GitHub API.
 * This class retrieves repository and branch details from the GitHub API.
 *
 * @since 22-08-2023
 * @author Wiktor Kulesza
 */
@Service
public class GithubApiService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @PostConstruct
    public void init() {
        SimpleModule module = new SimpleModule("GithubDeserializer");
        module.addDeserializer(UserRepositoryDetails.class, new UserRepositoryDetailsDeserializer());
        module.addDeserializer(BranchDetails.class, new BranchDetailsDeserializer());
        objectMapper.registerModule(module);
    }

    /**
     * Retrieves repository details for a given username.
     *
     * @param username The GitHub username.
     * @return List of UserRepositoryDetails representing user's repositories.
     * @throws JsonProcessingException if there's an issue with JSON processing.
     */
    public List<UserRepositoryDetails> getRepositoriesDetails(String username) throws JsonProcessingException {
        String result = getGithubApiResponse(getRepositoriesDetailsApiUrl(username));
        return Arrays.asList(objectMapper.readValue(result, UserRepositoryDetails[].class));
    }

    private String getGithubApiResponse(String apiUrl) {
        return restTemplate.getForObject(apiUrl, String.class);
    }

    private String getRepositoriesDetailsApiUrl(String username) {
        return ApiConstant.GITHUB_API_URL + ApiConstant.USERS + "/" + username + ApiConstant.REPOS;
    }

    /**
     * Retrieves branch details for a given repository.
     *
     * @param repository The user repository details.
     * @return List of BranchDetails representing repository's branches.
     * @throws GithubApiException if there's an issue with the GitHub API.
     */
    public List<BranchDetails> getBranchesDetails(UserRepositoryDetails repository) {
        try {
            String apiUrl = getBranchDetailsApiUrl(repository);
            String result = getGithubApiResponse(apiUrl);
            return Arrays.asList(objectMapper.readValue(result, BranchDetails[].class));
        } catch (JsonProcessingException e) {
            throw new GithubApiException(ResponseConstant.BRANCH_PARSING_ERROR, e);
        }
    }

    private String getBranchDetailsApiUrl(UserRepositoryDetails repository) {
        return ApiConstant.GITHUB_API_URL + ApiConstant.REPOS + "/" + repository.getLogin() + "/" + repository.getName() + ApiConstant.BRANCHES;
    }
}
