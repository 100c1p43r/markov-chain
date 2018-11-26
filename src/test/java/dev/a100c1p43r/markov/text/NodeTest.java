package dev.a100c1p43r.markov.text;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.ofNullable;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class NodeTest {
    @Test
    public void shouldCreateNodeWithWord() {
        Node node = new Node("test");
        assertThat(node.getWord()).isEqualTo("test");
    }

    @DataProvider
    public static Collection<Object> illegalWordProvider() {
        return Arrays.asList(null, "te st", "test4", "test,", ":");
    }

    @UseDataProvider("illegalWordProvider")
    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionForIllegalWord(String word) {
        new Node(word);
    }

    @Test
    public void shouldProperlyReturnRandomConnection() {
        List<Integer> numbers = range(0, 16).boxed().collect(Collectors.toList());
        Collections.shuffle(numbers);
        Node.random = new RandomMock(numbers);

        Node node = new Node("test");
        rangeClosed(1, 10).forEach(i -> node.incConnectionsCountForWord("this"));
        rangeClosed(1, 5).forEach(i -> node.incConnectionsCountForWord("that"));
        node.incConnectionsCountForWord("nothing");

        assertThat(node.getConnectionsTotal()).isEqualTo(16);
        Map<String, Integer> randomWords = new HashMap<>();

        for (int i = 0; i < 16; i++) {
            String word = node.getRandomWord();
            Integer cnt = ofNullable(randomWords.get(word)).orElse(0);
            randomWords.put(word, ++cnt);
        }

        assertThat(randomWords.get("this")).isEqualTo(10);
        assertThat(randomWords.get("that")).isEqualTo(5);
        assertThat(randomWords.get("nothing")).isEqualTo(1);
    }
}
