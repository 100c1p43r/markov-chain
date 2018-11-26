package dev.a100c1p43r.markov.text;

import java.util.HashMap;
import java.util.Map;

public class NodeRepository {
    private final Map<String, Node> nodes = new HashMap<>();
    private Node last = new Node(".");

    public boolean hasNode(String word) {
        return nodes.containsKey(word);
    }

    void addConnection(String word) {
        last.incConnectionsCountForWord(word);
        last = getNode(word);
    }

    Node getNode(String word) {
        return nodes.computeIfAbsent(word, Node::new);
    }

    Node getRandomNode(Node node) {
        return getNode(node.getRandomWord());
    }
}

