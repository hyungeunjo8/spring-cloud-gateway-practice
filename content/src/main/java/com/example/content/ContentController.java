package com.example.content;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
public class ContentController {

    @GetMapping("/contents")
    Mono<String> getHelloWorld() {
        log.info("hello~~contents!!!!!");
        return Mono.just("Hello World By Content");
    }

    @GetMapping("/contents/sleep")
    Mono<String> sleep() {
        try {
            Thread.sleep(10000L);
            return Mono.just("OK");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
