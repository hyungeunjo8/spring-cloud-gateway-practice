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
}
