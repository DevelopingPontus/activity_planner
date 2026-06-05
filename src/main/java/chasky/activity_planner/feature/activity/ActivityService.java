package chasky.activity_planner.feature.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class ActivityService {
    @Autowired
    private ActivityClient activityClient;

    @Value("${activity.api.key}")
    private String apiKey;

    public Mono<ActivitiesDTO> getActivity(String location, String category) {
        String url = String.format(
                    "https://api.geoapify.com/v2/places?categories=%s&bias=circle:%s,1500&limit=20&apiKey=%s",
               category, location, apiKey);
        return activityClient.getActivity(url);
    }
}
