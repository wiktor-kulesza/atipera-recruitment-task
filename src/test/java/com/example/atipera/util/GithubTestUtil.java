package com.example.atipera.util;

import com.example.atipera.model.BranchDetails;
import com.example.atipera.model.UserRepositoryDetails;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

public class GithubTestUtil {

    public static final String USERNAME = "testUsername";
    public static final String JSON_ACCEPT_HEADER = MediaType.APPLICATION_JSON_VALUE;
    public static final String XML_ACCEPT_HEADER = MediaType.APPLICATION_XML_VALUE;

    public static List<UserRepositoryDetails> getRepositoriesDetails() {
        List<UserRepositoryDetails> repositoriesDetails = new ArrayList<>();
        repositoriesDetails.add(new UserRepositoryDetails("repo1", "owner1", null));
        repositoriesDetails.add(new UserRepositoryDetails("repo2", "owner2", null));
        return repositoriesDetails;
    }

    public static List<BranchDetails> getBranchesDetails() {
        List<BranchDetails> branchesDetails = new ArrayList<>();
        branchesDetails.add(new BranchDetails("branch1", "sha1"));
        branchesDetails.add(new BranchDetails("branch2", "sha2"));
        return branchesDetails;
    }

}
