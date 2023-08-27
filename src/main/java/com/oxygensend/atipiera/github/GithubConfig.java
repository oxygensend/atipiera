package com.oxygensend.atipiera.github;

import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GithubConfig {

    @Value("${github.token}")
    private String githubToken;

    @Bean
    public GitHub githubConnection() {
        try {
            if (githubToken == null || githubToken.isBlank()) {
                return GitHub.connectAnonymously();
            }
            return GitHub.connectUsingOAuth(githubToken);
        } catch (Exception e) {
            throw new RuntimeException("Error while connecting to GitHub", e);
        }
    }

}
