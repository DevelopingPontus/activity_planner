package chasky.activity_planner.feature.weather_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {

    private Location location;
    private Current current;
    private Error error;


    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Location {
        private String name;
        private String country;
        @JsonProperty("lat")
        private double lat;
        @JsonProperty("lon")
        private double lon;

    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Current {
        @JsonProperty("feelslike_c")
        private double feelsLikeC;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Condition {
        private String text;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Error {
        private int code;
        private String message;
    }
}
