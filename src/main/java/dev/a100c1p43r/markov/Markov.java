package dev.a100c1p43r.markov;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class Markov extends SpringBootServletInitializer {

    public static void main(String... args) {
        SpringApplication.run(Markov.class);
    }

    /*@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Markov.class);
    }*/
}
