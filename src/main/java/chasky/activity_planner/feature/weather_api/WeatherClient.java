package chasky.activity_planner.feature.weather_api;

import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class WeatherClient {
    private final WebClient webClient;

    public Mono<WeatherApiDto> getWeather(String url) {
        return webClient.get().uri(url).retrieve().bodyToMono(WeatherApiDto.class);
    }
}
