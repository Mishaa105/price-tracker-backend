package io.github.Mishaa105.price_tracker.infrastructure.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class FrankfurterClient
{
    private final RestClient restClient;

    public FrankfurterClient(RestClient.Builder builder, @Value("${app.frankfurter.base-url}") String baseUrl)
    {
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public String getExchangeRatesData(String date)
    {
        return restClient.get().uri("/v2/rates?date=" + date +"&base=RUB&quotes=USD,EUR,TRY,UAH,IDR").retrieve().body(String.class);
    }
}
