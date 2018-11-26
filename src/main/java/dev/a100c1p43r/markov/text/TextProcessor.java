package dev.a100c1p43r.markov.text;


import java.util.Arrays;

public class TextProcessor {
    private static final int SENTENCE_SIZE_LIMIT = 512;
    private final NodeRepository repository;

    public TextProcessor(NodeRepository repository) {
        this.repository = repository;
    }

    public String buildText(String word) {
        StringBuilder sb = new StringBuilder(word);
        sb.append(" ");
        Node node = repository.getNode(word);
        int cnt = 0;

        while (!(node = repository.getRandomNode(node)).getWord().equals(".") && cnt < SENTENCE_SIZE_LIMIT) {
            sb.append(node.getWord()).append(" ");
            cnt++;
        }

        return sb.append('.').toString();
    }

    public void analyse(String text) {
        Arrays.stream(text.replaceAll("\\.", " .").split("\\s"))
                .map(s -> s.toLowerCase().replaceAll("[^a-z.]", ""))
                .filter(word -> word != null && !"".equals(word.trim()))
                .forEach(repository::addConnection);
    }
}
