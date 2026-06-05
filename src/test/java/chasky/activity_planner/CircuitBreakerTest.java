package chasky.activity_planner;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@SpringBootTest
public class CircuitBreakerTest {
    /**
     * To test this circuit breaker, we need to start the spring application without
     * exporting the API key of the service we want to test. More information in the README.md
     */

    OkHttpClient client = new OkHttpClient();

    @Test
    public void run() throws Exception {
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/malm%C3%B6")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful())
                        throw new IOException("Unexpected code " + response);

                    System.out.println(responseBody.string());
                }
            }
        });
    }
}
