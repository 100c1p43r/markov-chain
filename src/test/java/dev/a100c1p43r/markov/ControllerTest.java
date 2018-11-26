package dev.a100c1p43r.markov;

import dev.a100c1p43r.markov.text.TextProcessor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import dev.a100c1p43r.markov.text.NodeRepository;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class ControllerTest {
    private static final String TEST_TEXT = "This is some text, which should be processed. " +
            "To demonstrate, that this works.";

    @MockBean
    private NodeRepository repository;

    @MockBean
    private TextProcessor processor;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldGenerateSentence() throws Exception {
        when(processor.buildText(eq("this"))).thenReturn("this is some text");
        when(repository.hasNode(eq("this"))).thenReturn(true);

        mockMvc.perform(post("/dotheneedfull")
                .param("text", TEST_TEXT)
                .param("word", "This"))
                .andExpect(status().isOk())
                .andExpect(content().string("this is some text"));

        verify(processor).analyse(TEST_TEXT);
    }

}
