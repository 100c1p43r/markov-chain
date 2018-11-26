package dev.a100c1p43r.markov.text;

import org.junit.Test;

import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TextProcessingTest {
    private static final String TEST_TEXT =
            "This is a text. " +
            "Text is important for this test. " +
            "This text is created to be important for humans and test testing this text functionality. " +
            "To be or not to be, either to test or not to test.";

    @Test
    public void shouldProcessText() {
        NodeRepository repository = new NodeRepository();
        TextProcessor processor = new TextProcessor(repository);

        processor.analyse(TEST_TEXT);
        String outText = processor.buildText("this");
        Set<String> textWords = Arrays.stream(TEST_TEXT.toLowerCase()
                .replaceAll("[,.]", "").split(" "))
                .collect(Collectors.toSet());
        textWords.add(".");

        for (String word : textWords) {
            assertTrue(repository.hasNode(word));
        }

        assertThat(outText).startsWith("this").endsWith(".")
                .isLowerCase().containsPattern(Pattern.compile("^[a-z\\s\\.]+$"));
    }
}
