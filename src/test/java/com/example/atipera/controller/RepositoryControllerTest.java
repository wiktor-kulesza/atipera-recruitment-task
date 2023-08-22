package com.example.atipera.controller;

import com.example.atipera.constant.ResponseConstant;
import com.example.atipera.controller.RepositoryController;
import com.example.atipera.exception.GithubApiException;
import com.example.atipera.model.ErrorResponse;
import com.example.atipera.service.RepositoryDetailsService;
import com.example.atipera.util.GithubTestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RepositoryControllerTest {

    @Mock
    private RepositoryDetailsService repositoryDetailsService;

    @InjectMocks
    private RepositoryController repositoryController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserRepositoriesDetails_OnXMLHeader_ReturnUnacceptableResponseStatus() {
        //given
        String acceptHeader = GithubTestUtil.XML_ACCEPT_HEADER;

        //when
        ResponseEntity<?> response = repositoryController.getUserRepositoriesDetails(acceptHeader, GithubTestUtil.USERNAME);

        //then
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testGetUserRepositoriesDetails_OnHappyPath_ReturnSuccess() {
        //given
        when(repositoryDetailsService.getUserRepositoriesDetails(GithubTestUtil.USERNAME)).thenReturn(GithubTestUtil.getRepositoriesDetails());

        //when
        ResponseEntity<?> response = repositoryController.getUserRepositoriesDetails(GithubTestUtil.JSON_ACCEPT_HEADER, GithubTestUtil.USERNAME);

        //then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testGetUserRepositoriesDetails_InternalServerError() {
        //given
        when(repositoryDetailsService.getUserRepositoriesDetails(GithubTestUtil.USERNAME)).thenThrow(new GithubApiException("Test exception"));

        //when
        ResponseEntity<?> response = repositoryController.getUserRepositoriesDetails(GithubTestUtil.JSON_ACCEPT_HEADER, GithubTestUtil.USERNAME);

        //then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
