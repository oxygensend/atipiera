package com.oxygensend.atipiera.mapper;


import com.oxygensend.atipiera.dto.BranchDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kohsuke.github.GHBranch;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BranchMapperTest {

    @InjectMocks
    private BranchMapper branchMapper;


    @Test
    public void testMap() {
        // Arrange
        var ghBranch = mockBranch();
        var expectedBranch = new BranchDto("branchName", "sha1");

        // Act
        var branch = branchMapper.map(ghBranch);

        // Assert
        assertEquals(expectedBranch, branch);
    }

    @Test
    public void testMapCollection() {
        // Arrange
        var ghBranch = mockBranch();
        Collection<GHBranch> ghBranches = new ArrayList<>();
        ghBranches.add(ghBranch);

        var expectedBranch = new BranchDto("branchName", "sha1");
        List<BranchDto> expectedBranches = new ArrayList<>();
        expectedBranches.add(expectedBranch);

        // Act
        var branches = branchMapper.map(ghBranches);

        // Assert
        assertEquals(expectedBranches, branches);
    }

    private GHBranch mockBranch() {
        var ghBranch = mock(GHBranch.class);
        when(ghBranch.getName()).thenReturn("branchName");
        when(ghBranch.getSHA1()).thenReturn("sha1");

        return ghBranch;
    }

}
