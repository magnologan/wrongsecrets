package org.owasp.wrongsecrets;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
    properties = {"K8S_ENV=Heroku(Docker)"},
    classes = WrongSecretsApplication.class
)
@AutoConfigureMockMvc
public class challengeUILimittedTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void shouldProvideExplanationWithLimmitForChallenge8GivenChallengeIsLimittedWhenHosted() throws Exception {
        mvc.perform(get("/challenge/8").with(csrf()))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("PLEASE NOTE: you are running this challenge on a hosted version of WrongSecrets")));
    }
}
