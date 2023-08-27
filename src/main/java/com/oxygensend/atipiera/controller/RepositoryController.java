package com.oxygensend.atipiera.controller;

import com.oxygensend.atipiera.dto.RepositoryListDto;
import com.oxygensend.atipiera.enums.SupportedAcceptHeader;
import com.oxygensend.atipiera.exception.NotSupportedHeaderException;
import com.oxygensend.atipiera.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/repositories")
public class RepositoryController {

    private final RepositoryService repositoryService;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public RepositoryListDto getRepositories(
            @PathVariable String username,
            @RequestHeader("Accept") String acceptHeader
    ) throws IOException {

        if (!SupportedAcceptHeader.isHeaderSupported(acceptHeader)) {
            throw new NotSupportedHeaderException(acceptHeader);
        }

        return repositoryService.getRepositories(username);
    }

}
