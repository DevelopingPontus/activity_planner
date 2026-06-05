# API Documentation

## Running the program

1. Get API keys from the following services and add them to your environment variables:

[WeatherApi](https://www.weatherapi.com/my/)

```bash
export WEATHER_API_KEY="your-key"
```

[Geoapify](https://Geoapify.com/api)

```bash
export ACTIVITY_API_KEY="your-key"
```

2. Run the application with:

```bash
mvn spring-boot:run
```

3. You can then reach the api via this url with parameter city="location_you_want_search_for":
   <http://localhost:8080/api/v1/recommendations?city=>

## Testing

For testing:

```bash
mvn test
```
