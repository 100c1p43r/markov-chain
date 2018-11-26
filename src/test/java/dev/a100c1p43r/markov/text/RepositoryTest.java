package dev.a100c1p43r.markov.text;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RepositoryTest {

    @Test
    public void shouldAddNodesAsConnections() {
        NodeRepository repository = new NodeRepository();
        repository.addConnection("this");
        repository.addConnection("is");
        repository.addConnection("a");
        repository.addConnection("test");

        assertTrue(repository.hasNode("this"));
        assertTrue(repository.hasNode("is"));
        assertTrue(repository.hasNode("a"));
        assertTrue(repository.hasNode("test"));
    }
}
