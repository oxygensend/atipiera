package com.oxygensend.atipiera.mapper;

import com.oxygensend.atipiera.dto.RepositoryDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kohsuke.github.GHBranch;
import org.kohsuke.github.GHRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RepositoryMapperTest {
    @Mock
    private BranchMapper branchMapper;

    @InjectMocks
    private RepositoryMapper repositoryMapper;


    @Test
    public void testMap() throws IOException {
        // Arrange
        Collection<GHRepository> ghRepositories = new ArrayList<>();
        when(branchMapper.map(new ArrayList<GHBranch>())).thenReturn(new ArrayList<>());
        var ghRepository = mockRepository();


        var expectedRepository = new RepositoryDto("repo1", "owner", new ArrayList<>());

        // Act
        var repository = repositoryMapper.map(ghRepository);

        // Assert
        assertEquals(expectedRepository, repository);

    }

    @Test
    public void testMapCollection() {
        // Arrange
        // Act
        // Assert
    }

    private GHRepository mockRepository() {
        var ghRepository = mock(GHRepository.class);
        when(ghRepository.getName()).thenReturn("repo1");
        when(ghRepository.getOwnerName()).thenReturn("owner");
        return ghRepository;
    }
}

