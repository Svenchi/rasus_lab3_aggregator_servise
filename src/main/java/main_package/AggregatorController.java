package main_package;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

@RestController
public class AggregatorController {

    private final AggregatorService aggregatorService;

    public AggregatorController(AggregatorService temperatureService) {
        this.aggregatorService = temperatureService;
    }

    @GetMapping("/connect")
    public String connect(){
        return "Great job";
    }

    @GetMapping("/reading")
    public String getReading() {
        Gson gson = new Gson();
        return gson.toJson(aggregatorService.getMeasurement());
    }
}