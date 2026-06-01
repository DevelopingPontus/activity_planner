package chasky.activity_planner.feature.recommendation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chasky.activity_planner.feature.weather_api.WeatherDto;
import chasky.activity_planner.feature.weather_api.WeatherService;
import reactor.core.publisher.Mono;

@Service
public class RecommendationService {

    @Autowired
    private WeatherService weatherService;

    // public RecommendationDTO getRecommendation(String city) {
    //     WeatherApiDto weather = weatherService.getWeather(city);
    //     // ActivityDTO activity = 
    // }

    public Mono<WeatherDto> getRecommendation(String city) {
        return weatherService.getWeather(city);
        // ActivityDTO activity =
    }
    
}
