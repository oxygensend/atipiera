package com.oxygensend.atipiera.service;

import com.oxygensend.atipiera.dto.RepositoryListDto;
import com.oxygensend.atipiera.github.GithubClient;
import com.oxygensend.atipiera.mapper.RepositoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
@RequiredArgsConstructor
public class RepositoryService {

    private final GithubClient githubClient;
    private final RepositoryMapper repositoryMapper;


    public RepositoryListDto getRepositories(String username) throws IOException {
        var repositories = githubClient.getRepositories(username).values();
        var unForkedRepositories = repositories.stream()
                .filter(repository -> !repository.isFork())
                .toList();

        var repositoriesDto = repositoryMapper.map(unForkedRepositories);
        return new RepositoryListDto(repositoriesDto, repositoriesDto.size());
    }
}
