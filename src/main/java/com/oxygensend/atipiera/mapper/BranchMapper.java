package com.oxygensend.atipiera.mapper;

import com.oxygensend.atipiera.dto.BranchDto;
import org.kohsuke.github.GHBranch;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class BranchMapper implements Mapper<GHBranch, BranchDto> {
    @Override
    public BranchDto map(GHBranch ghBranch) {
        return new BranchDto(ghBranch.getName(), ghBranch.getSHA1());
    }

    @Override
    public List<BranchDto> map(Collection<GHBranch> t) {
        var branches = new ArrayList<BranchDto>();
        for (GHBranch ghBranch : t) {
            branches.add(map(ghBranch));
        }
        return branches;
    }
}
