package chasky.activity_planner.feature.weather_api;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class WeatherClient {
    private final WebClient webClient;

    public WeatherClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Mono<WeatherDto> getWeather(String url) {
        return webClient.get().uri(url).retrieve().bodyToMono(WeatherDto.class);
    }
}
