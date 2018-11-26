package dev.a100c1p43r.markov.text;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static java.util.Optional.ofNullable;

class Node {
    private static final Pattern wordPattern = Pattern.compile("[a-z.]+");
    static Random random = new Random();

    private final String word;
    private int connectionsTotal = 0;
    private Map<String, Integer> connections = new HashMap<>();

    Node(String word) {
        if (word == null || !wordPattern.matcher(word).matches()) {
            throw new IllegalArgumentException("Word '" + word + "' argument must contain letters or dot character.");
        }
        this.word = word;
    }

    String getRandomWord() {
        final int rnd = random.nextInt(connectionsTotal);
        final AtomicInteger i = new AtomicInteger(0);
        return connections.entrySet().stream()
                .filter(e -> rnd < i.addAndGet(e.getValue()))
                .findFirst().get().getKey();
    }

    int getConnectionsTotal() {
        return connectionsTotal;
    }

    void incConnectionsCountForWord(String word) {
        Integer cnt = ofNullable(connections.get(word)).orElse(0);
        connectionsTotal++;
        connections.put(word, ++cnt);
    }

    String getWord() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;
        return word.equals(node.word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

}
