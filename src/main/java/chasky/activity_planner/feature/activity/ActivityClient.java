package chasky.activity_planner.feature.activity;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Component
public class ActivityClient {
    private final WebClient webClient;

    public ActivityClient(WebClient.Builder builder) {
        this.webClient = builder.build();
    }

    public Mono<ActivitiesDTO> getActivity(String url) {
        return webClient.get().uri(url).retrieve().bodyToMono(ActivitiesDTO.class);
    }
}
