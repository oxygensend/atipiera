package com.oxygensend.atipiera.service;

import com.oxygensend.atipiera.dto.RepositoryDto;
import com.oxygensend.atipiera.github.GithubClient;
import com.oxygensend.atipiera.mapper.RepositoryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepositoryServiceTest {
    @Mock
    private GithubClient githubClient;
    @Mock
    private RepositoryMapper repositoryMapper;
    @InjectMocks
    private RepositoryService repositoryService;


    @Test
    public void testGetRepositories() throws IOException {
        // Arrange
        Map<String, GHRepository> repos = new HashMap<>();
        var ghRepository = mock(GHRepository.class);
        repos.put("repo1", ghRepository);
        when(ghRepository.getName()).thenReturn("repo1");
        when(ghRepository.getOwnerName()).thenReturn("owner");
        when(ghRepository.isFork()).thenReturn(false);

        var dtoList = new ArrayList<RepositoryDto>();
        dtoList.add(new RepositoryDto("repo1", "owner", new ArrayList<>()));

        var ghUser = mock(GHUser.class);
        when(githubClient.getRepositories("username")).thenReturn(repos);
        when(repositoryMapper.map(repos.values())).thenReturn(dtoList);
        // Act
        var repositories = repositoryService.getRepositories("username");
        // Assert
        assertEquals(dtoList, repositories.repositories());
    }

}
