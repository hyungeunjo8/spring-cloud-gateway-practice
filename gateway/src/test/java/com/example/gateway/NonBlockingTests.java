package com.example.gateway;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class NonBlockingTests {

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("reactor.netty.ioWorkerCount", "4");
    }

    private final WebTestClient webTestClient = WebTestClient.bindToServer()
            .baseUrl("http://localhost:8080")
            .responseTimeout(Duration.ofSeconds(30000))
            .build();

    @Test
    void testOK() {
        webTestClient.get().uri("/contents")
                .header(HttpHeaders.AUTHORIZATION, "123")
                .exchange()
                .expectStatus().isOk();
    }

    @RepeatedTest(10)
    void repeatedTest() {
        webTestClient.get().uri("/contents")
                .header(HttpHeaders.AUTHORIZATION, "123")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void sleepApiRepeatedTest() {
        webTestClient.get().uri("/contents/sleep")
                .header(HttpHeaders.AUTHORIZATION, "123")
                .exchange()
                .expectStatus().isOk();
    }

}
