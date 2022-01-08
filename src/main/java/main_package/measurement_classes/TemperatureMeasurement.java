package main_package.measurement_classes;

import com.google.gson.Gson;

public class TemperatureMeasurement {
    private final String type;  //nije static jer ima nekih problema sa serijalizacijom
    private String measurementType;
    private int temperature;


    public TemperatureMeasurement(String measurement, int temperature) {
        this.measurementType = measurement;
        this.temperature = temperature;
        this.type = "temperature";
    }

    public static TemperatureMeasurement fromJson(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, TemperatureMeasurement.class);
    }

    public void celsiusToKelvin() throws Exception {
        if (!this.measurementType.equals("C")){
            throw new Exception("cannot convert from Celsius if measurement isn't in Celsius!");
        }
        this.measurementType = "K";
        this.temperature += 273;
    }
}
