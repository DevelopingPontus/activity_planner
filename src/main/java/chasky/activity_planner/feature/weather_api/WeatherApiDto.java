package chasky.activity_planner.feature.weather_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiDto {
    
    private Location location;
    private Current current;
    private Error error;

    public boolean hasError() {
        return error != null;
    }

    public boolean hasLocation() {
        return location != null && current != null;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Location {
        private String name;
        private String country;
        private double lat;
        private double lon;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Current {
        @JsonProperty("temp_c")
        private double tempC;
        @JsonProperty("temp_f")
        private double tempF;
        private Condition condition;
        private int humidity;
        @JsonProperty("wind_kph")
        private double windKph;
        @JsonProperty("is_day")
        private int isDay;
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
