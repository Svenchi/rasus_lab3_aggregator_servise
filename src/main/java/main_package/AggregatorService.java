package main_package;

import main_package.measurement_classes.AgregatedMeasurements;
import main_package.measurement_classes.HumidityMeasurement;
import main_package.measurement_classes.TemperatureMeasurement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AggregatorService {
    private static final String KELVIN = "K";
    private static final String CELSIUS = "C";

    @Value("${humidity.endpoint}")
    private String humidityEndpoint;
    @Value("${temperature.endpoint}")
    private String temperatureEndpoint;

    private HttpClient httpClient;

    public AggregatorService() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public AgregatedMeasurements getMeasurement(){
        try{
            TemperatureMeasurement tm = getTempResponse();
            HumidityMeasurement hm = getHumidityResponse();
            try{tm.celsiusToKelvin();} catch (Exception ignored){}
            return new AgregatedMeasurements(tm, hm);

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private TemperatureMeasurement getTempResponse() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(
                URI.create(temperatureEndpoint))
                .header("accept", "application/json")
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return TemperatureMeasurement.fromJson(response.body());
    }

    private HumidityMeasurement getHumidityResponse() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder(
                URI.create(humidityEndpoint))
                .header("accept", "application/json")
                .build();

        var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return HumidityMeasurement.fromJson(response.body());
    }
}
