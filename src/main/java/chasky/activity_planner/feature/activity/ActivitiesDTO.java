package chasky.activity_planner.feature.activity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ActivitiesDTO {
    private Features[] features;

    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Features {
        private Properties properties;
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public static class Properties {
        private String name;
        private String[] categories;
        private String formatted;
    }
}
