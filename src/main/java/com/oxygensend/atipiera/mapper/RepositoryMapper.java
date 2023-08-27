package com.oxygensend.atipiera.mapper;

import com.oxygensend.atipiera.dto.RepositoryDto;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHRepository;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RequiredArgsConstructor
@Component
public class RepositoryMapper implements Mapper<GHRepository, RepositoryDto> {

    private final BranchMapper branchMapper;


    @Override
    public RepositoryDto map(GHRepository ghRepository) throws IOException {
        var ghBranches = ghRepository.getBranches();
        var branches = branchMapper.map(ghBranches.values());
        return new RepositoryDto(ghRepository.getName(), ghRepository.getOwnerName(), branches);
    }

    @Override
    public List<RepositoryDto> map(Collection<GHRepository> t) throws IOException {
        var repositories = new ArrayList<RepositoryDto>();
        for (GHRepository ghRepository : t) {
            repositories.add(map(ghRepository));
        }
        return repositories;
    }
}
