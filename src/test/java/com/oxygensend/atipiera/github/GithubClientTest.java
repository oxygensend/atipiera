package com.oxygensend.atipiera.github;


import com.oxygensend.atipiera.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kohsuke.github.GHFileNotFoundException;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class GithubClientTest {

    @InjectMocks
    private GithubClient githubClient;
    @Mock
    private GitHub githubConnection;


    @Test
    public void testGetRepositories() throws IOException {

        // Arrange
        Map<String, GHRepository> repos = new HashMap<>();
        repos.put("repo1", mock(GHRepository.class));
        var ghUser = mock(GHUser.class);
        when(ghUser.getRepositories()).thenReturn(repos);
        when(githubConnection.getUser("username")).thenReturn(ghUser);

        // Act
        var repositories = githubClient.getRepositories("username");

        // Assert
        verify(githubConnection, times(1)).getUser("username");
        verify(githubConnection.getUser("username"), times(1)).getRepositories();

        assertEquals(repos.get("repo1"), repositories.get("repo1"));
        assertEquals(1, repositories.size());
    }

    @Test
    public void testGetRepositoriesUserNotFound() throws IOException {

        // Arrange

        when(githubConnection.getUser("username")).thenThrow(new GHFileNotFoundException("User not found"));

        // Act && Assert
        assertThrows(UserNotFoundException.class, () -> githubClient.getRepositories("username"));
        verify(githubConnection, times(1)).getUser("username");

    }
}
