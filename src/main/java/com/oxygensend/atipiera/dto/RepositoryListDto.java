package com.oxygensend.atipiera.dto;

import java.util.List;

public record RepositoryListDto(List<RepositoryDto> repositories, int iTotalRecords) {
}
