package chasky.activity_planner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import chasky.activity_planner.feature.activity.ActivitiesDTO;
import chasky.activity_planner.feature.recommendation.RecommendationController;
import chasky.activity_planner.feature.recommendation.RecommendationService;

@SpringBootTest
public class RecommendationIntegrationTest {

    private WebTestClient webTestClient;
    @Autowired
    private RecommendationService recommendationService;

    @BeforeEach
    public void setUp() {
        webTestClient = WebTestClient.bindToController(new RecommendationController(recommendationService)).build();
    }

    @Test
    void testGetFallBackRecommendedActivity() throws Exception {
        webTestClient.get().uri("/api/v1/recommendations?city=malmö")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ActivitiesDTO.class).isEqualTo(recommendationService.fallBackActivitiesDTO());
    }

    @Test
    void testNullPathReturnsNOT_FOUND() throws Exception {
        webTestClient.get().uri("/api/v1/recommendations?city=")
                .exchange()
                .expectStatus().isBadRequest();
    }
}
