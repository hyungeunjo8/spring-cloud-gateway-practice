package com.example.review;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class ReviewController {

    @GetMapping("/reviews")
    Mono<String> getHelloWorld() {
        log.info("hello~~reviews!!!!!");
        return Mono.just("Hello World By Review");
    }
}
