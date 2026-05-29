package chasky.activity_planner.feature.weather_api;

public class WeatherService {

    String url = String.format(
            "https://api.weatherapi.com/v1/current.json?key=%s&q=%s&lang=%s",
            properties.getApiKey(), city, properties.getLang());

}
