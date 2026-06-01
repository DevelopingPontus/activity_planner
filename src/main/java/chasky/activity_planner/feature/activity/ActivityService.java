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

    public Mono<ActivityDTO> getActivity(String city) {
        String url = String.format(
                "http://api.weatherapi.com/v1/current.json?key=%s&q=%s&aqi=no",
                apiKey, city);
        return activityClient.getActivity(url);
    }

}
