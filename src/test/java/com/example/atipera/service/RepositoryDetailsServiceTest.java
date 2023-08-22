package com.example.atipera.service;

import static org.mockito.Mockito.*;

import com.example.atipera.model.UserRepositoryDetails;
import com.example.atipera.util.GithubTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RepositoryDetailsServiceTest {

    @Mock
    private GithubApiService githubApiService;

    @InjectMocks
    private RepositoryDetailsService repositoryDetailsService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserRepositoriesDetails_HasValidResponse() throws Exception {
        //given
        String username = GithubTestUtil.USERNAME;
        when(githubApiService.getRepositoriesDetails(username)).thenReturn(GithubTestUtil.getRepositoriesDetails());
        when(githubApiService.getBranchesDetails(any())).thenReturn(GithubTestUtil.getBranchesDetails());

        //when
        List<UserRepositoryDetails> result = repositoryDetailsService.getUserRepositoriesDetails(GithubTestUtil.USERNAME);

        //then
        assertNotNull(result);
        assertEquals(GithubTestUtil.getRepositoriesDetails().size(), result.size());
        assertEquals(GithubTestUtil.getRepositoriesDetails().get(0).getName(), result.get(0).getName());
        assertEquals(GithubTestUtil.getRepositoriesDetails().get(0).getLogin(), result.get(0).getLogin());
        assertEquals(GithubTestUtil.getBranchesDetails().get(0).getName(), result.get(0).getBranches().get(0).getName());
        assertEquals(GithubTestUtil.getBranchesDetails().get(0).getLastCommitSha(), result.get(0).getBranches().get(0).getLastCommitSha());
    }



}
