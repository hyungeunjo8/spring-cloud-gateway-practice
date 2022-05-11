package com.example.content;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
public class ContentController {

    @GetMapping("/contents")
    String getHelloWorld() {
        log.info("hello~~contents!!!!!");
        return "Hello World By Content";
    }

    @GetMapping("/contents/slow")
    Mono<String> slow() {
        return Mono.fromSupplier(this::blockingFunction);
    }

    private String blockingFunction() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "OK";
    }

    @GetMapping("/block-filter")
    Mono<String> block() {
        return Mono.just("OK");
    }
}
