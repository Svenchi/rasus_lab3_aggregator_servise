package main_package.measurement_classes;

import com.google.gson.Gson;

public class HumidityMeasurement {
    private final String type;  //nije static jer ima nekih problema sa serijalizacijom
    private final String measurementType;
    private int humidity;


    public HumidityMeasurement(String measurement, int humidity) {
        this.measurementType = measurement;
        this.humidity = humidity;
        this.type = "humidity";
    }

    public static HumidityMeasurement fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, HumidityMeasurement.class);
    }
}
