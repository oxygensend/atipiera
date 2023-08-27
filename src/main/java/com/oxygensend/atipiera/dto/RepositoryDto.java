package com.oxygensend.atipiera.dto;

import java.util.List;

public record RepositoryDto(String name, String owner, List<BranchDto> branches) {
}
