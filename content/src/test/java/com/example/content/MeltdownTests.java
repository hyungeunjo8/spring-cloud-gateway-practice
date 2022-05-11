package com.example.content;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@Execution(ExecutionMode.CONCURRENT)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class MeltdownTests {

	@BeforeAll
	public static void beforeAll() {
		System.setProperty("reactor.netty.ioWorkerCount", "4");
	}

	private final WebTestClient webTestClient = WebTestClient.bindToServer()
			.baseUrl("http://localhost:7777")
			.responseTimeout(Duration.ofSeconds(30000))
			.build();


	@DisplayName("정상 API 호출")
	@RepeatedTest(20)
	void repeatedTest() {
		webTestClient.get().uri("/contents")
				.exchange()
				.expectStatus().isOk();
	}

	@DisplayName("Content Slow API 호출 (thread sleep 1000)")
	@Test
	void slowApiRepeatedTest() {
		webTestClient.get().uri("/contents/slow")
				.exchange()
				.expectStatus().isOk();
	}
}
