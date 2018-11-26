package dev.a100c1p43r.markov.setup;

import dev.a100c1p43r.markov.text.TextProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import dev.a100c1p43r.markov.text.NodeRepository;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;
import static org.springframework.web.context.WebApplicationContext.SCOPE_REQUEST;

@Configuration
public class Setup {

    @Bean
    @Scope(value = SCOPE_REQUEST, proxyMode = TARGET_CLASS)
    public NodeRepository createRepository() {
        return new NodeRepository();
    }

    @Bean
    @Scope(value = SCOPE_REQUEST, proxyMode = TARGET_CLASS)
    public TextProcessor createProcesor(@Autowired NodeRepository repository) {
        return new TextProcessor(repository);
    }

}
