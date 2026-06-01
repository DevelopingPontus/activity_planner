package chasky.activity_planner.feature.recommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

import chasky.activity_planner.feature.weather_api.WeatherDto;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    // @PostMapping("/{city")
    // public RecommendationDTO toGetRecommendedActivity(@RequestBody @PathVariable
    // String city) {
    // return recommendationService.getRecommendation(city);
    // }

    @GetMapping("{city}")
    public Mono<WeatherDto> toGetRecommendedActivity(@RequestBody @PathVariable String city) {
        System.out.println(city);
        return recommendationService.getRecommendation(city);
    }

}
