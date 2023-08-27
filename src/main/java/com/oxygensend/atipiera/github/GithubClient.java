package com.oxygensend.atipiera.github;

import com.oxygensend.atipiera.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.kohsuke.github.GHFileNotFoundException;
import org.kohsuke.github.GHRepository;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;


@Component
@RequiredArgsConstructor
public class GithubClient {

    private final GitHub githubConnection;


    public Map<String, GHRepository> getRepositories(String username) throws IOException {
        var user = getUser(username);
        return user.getRepositories();
    }

    private GHUser getUser(String username) {
        try {
            return githubConnection.getUser(username);
        } catch (IOException e) {
            throw new UserNotFoundException(username);
        }
    }
}
