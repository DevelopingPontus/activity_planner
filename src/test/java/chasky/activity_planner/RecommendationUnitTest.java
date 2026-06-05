package chasky.activity_planner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import chasky.activity_planner.feature.activity.ActivitiesDTO;
import chasky.activity_planner.feature.recommendation.RecommendationController;
import chasky.activity_planner.feature.recommendation.RecommendationService;
import chasky.activity_planner.feature.weather_api.WeatherDto;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest

public class RecommendationUnitTest {

    private WebTestClient webTestClient;
    private RecommendationService recommendationService;

    @BeforeEach
    public void setUp() {
        recommendationService = mock(RecommendationService.class);
        webTestClient = WebTestClient.bindToController(new RecommendationController(recommendationService)).build();
    }

    @Test
    void testGetRecommendedActivity() throws Exception {
        when(recommendationService.getRecommendation("malmö")).thenReturn(Mono.just(new ActivitiesDTO()));

        webTestClient.get().uri("/api/v1/recommendations?city=malmö")
                .exchange()
                .expectStatus().isOk()
                .expectBody(ActivitiesDTO.class);
    }

    @Test
    void testGetWeather() throws Exception {
        when(recommendationService.getWeather("malmö")).thenReturn(Mono.just(new WeatherDto()));

        webTestClient.get().uri("/api/v1/recommendations?city=malmö")
                .exchange()
                .expectStatus().isOk()
                .expectBody(WeatherDto.class);
    }

}
