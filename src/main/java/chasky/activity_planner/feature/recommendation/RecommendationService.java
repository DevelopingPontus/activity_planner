package chasky.activity_planner.feature.recommendation;

import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.stereotype.Service;

import chasky.activity_planner.feature.activity.ActivitiesDTO;
import chasky.activity_planner.feature.activity.ActivityService;
import chasky.activity_planner.feature.weather_api.WeatherDto;
import chasky.activity_planner.feature.weather_api.WeatherDto.Current;
import chasky.activity_planner.feature.weather_api.WeatherDto.Location;
import chasky.activity_planner.feature.weather_api.WeatherService;
import reactor.core.publisher.Mono;

import chasky.activity_planner.feature.activity.ActivitiesDTO.Features;
import chasky.activity_planner.feature.activity.ActivitiesDTO.Properties;

@Service
public class RecommendationService {

    private final WeatherService weatherService;
    private final ActivityService activityService;
    private final ReactiveCircuitBreaker weatherCircuitBreaker;
    private final ReactiveCircuitBreaker activitiesCircuitBreaker;

    public RecommendationService(
            WeatherService weatherService,
            ActivityService activityService,
            ReactiveCircuitBreakerFactory<?, ?> circuitBreakerFactory) {
        this.weatherService = weatherService;
        this.activityService = activityService;
        this.weatherCircuitBreaker = circuitBreakerFactory.create("weather");
        this.activitiesCircuitBreaker = circuitBreakerFactory.create("activities");
    }

    public Mono<ActivitiesDTO> getRecommendation(String city) {
        return getWeather(city)
                .flatMap(weather -> getActivities(
                        weather.getLocation().getLon(),
                        weather.getLocation().getLat(),
                        pickCategory(weather.getCurrent().getFeelsLikeC())));
    }

    public Mono<WeatherDto> getWeather(String city) {
        return weatherCircuitBreaker.run(
                weatherService.getWeather(city),
                throwable -> {
                    // Fallback: if weather service fails, return a default weather (e.g., 15°C,
                    // Malmö as location)
                    System.out.println("Weather service failed. Falling back to 15C, lon:13.0, lat:55.6.");
                    WeatherDto fallbackWeatherDto = fallBackWeatherDto();
                    return Mono.just(fallbackWeatherDto);
                });
    }

    public Mono<ActivitiesDTO> getActivities(double lon, double lat, String category) {
        String location = String.format("%s,%s", lon, lat);
        return activitiesCircuitBreaker.run(
                activityService.getActivity(location, category),
                throwable -> {
                    // Fallback: if activities service fails, return a default activity list
                    System.out.println("Activities service failed. Falling back to default activities.");
                    ActivitiesDTO fallbackActivitiesDto = fallBackActivitiesDTO();
                    return Mono.just(fallbackActivitiesDto);
                });
    }

    public String pickCategory(double feelsLikeC) {
        if (feelsLikeC > 25) {
            return "beach";
        } else if (feelsLikeC > 15) {
            return "leisure.park";
        } else if (feelsLikeC > 5) {
            return "entertainment.museum";
        } else {
            return "heritage";
        }
    }

    public WeatherDto fallBackWeatherDto() {
        WeatherDto fallbackWeatherDto = new WeatherDto();
        fallbackWeatherDto.setCurrent(new Current());
        fallbackWeatherDto.getCurrent().setFeelsLikeC(15);
        fallbackWeatherDto.setLocation(new Location());
        fallbackWeatherDto.getLocation().setLat(55.6);
        fallbackWeatherDto.getLocation().setLon(13.0);
        return fallbackWeatherDto;
    }

    public ActivitiesDTO fallBackActivitiesDTO() {
        ActivitiesDTO fallbackActivitiesDto = new ActivitiesDTO();
        Features features = new Features();
        Properties properties = new Properties();
        properties.setFormatted("Activities API is down. Take a hike");
        features.setProperties(properties);
        fallbackActivitiesDto.setFeatures(new Features[] { features });
        return fallbackActivitiesDto;
    }

}
