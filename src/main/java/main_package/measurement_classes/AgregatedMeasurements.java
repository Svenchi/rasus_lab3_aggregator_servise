package main_package.measurement_classes;

public class AgregatedMeasurements {
    private final TemperatureMeasurement temperatureMeasurement;
    private final HumidityMeasurement humidityMeasurement;

    public AgregatedMeasurements(TemperatureMeasurement temperatureMeasurement, HumidityMeasurement humidityMeasurement) {
        this.temperatureMeasurement = temperatureMeasurement;
        this.humidityMeasurement = humidityMeasurement;
    }
}
