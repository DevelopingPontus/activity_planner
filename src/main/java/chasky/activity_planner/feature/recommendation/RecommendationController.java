package chasky.activity_planner.feature.recommendation;

import org.springframework.web.bind.annotation.RestController;

import chasky.activity_planner.feature.activity.ActivitiesDTO;
import jakarta.validation.constraints.NotBlank;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/recommendations")
public class RecommendationController {
    private RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @GetMapping
    public Mono<ActivitiesDTO> toGetRecommendedActivity(@RequestParam(value = "city", required = true) @NotBlank String city) {
        return recommendationService.getRecommendation(city);
    }

}
