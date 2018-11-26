package dev.a100c1p43r.markov;

import dev.a100c1p43r.markov.text.TextProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import dev.a100c1p43r.markov.text.NodeRepository;


@RestController
class MainController {
    @Autowired
    private NodeRepository repository;

    @Autowired
    private TextProcessor processor;

    @RequestMapping(value = "/dotheneedfull", method = RequestMethod.POST)
    public ResponseEntity handleRrequest(@RequestParam String text, @RequestParam String word) {
        processor.analyse(text);

        word = word.toLowerCase();

        if (!repository.hasNode(word)) {
            return ResponseEntity.badRequest().body("Can't find word: " + word);
        }

        return ResponseEntity.ok(processor.buildText(word));
    }
}
