package ru.spbau.devdays2013.WeatherOracle.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dmitriy Bandurin
 *         Date: 13.05.13
 */
public class PredictWeatherBean {
    public final static String TEMPERATURE = "TEMPERATURE";
    public final static String COMFORT_TEMPERATURE = "COMFORT_TEMPERATURE";
    public final static String WATER_TEMPERATURE = "WATER_TEMPERATURE";
    public final static String WIND_SPEED ="WIND_SPEED";
    public final static String WIND_DIRECTION ="WIND_DIRECTION";
    public final static String PRESSURE ="PRESSURE";
    public final static String SOURCE ="SOURCE";

    private int temperature;
    private String source;
    private int comfortTemperature;
    private int waterTemperature;
    private int windSpeed;
    private WindDirection windDirection;
    private int pressure;

    public PredictWeatherBean(int temperature, int windSpeed, int comfortTemperature, int waterTemperature, WindDirection windDirection, int pressure, String source) {
        this.temperature = temperature;
        this.windSpeed = windSpeed;
        this.comfortTemperature = comfortTemperature;
        this.waterTemperature = waterTemperature;
        this.windDirection = windDirection;
        this.pressure = pressure;
        this.source = source;
    }

    public static Map<String, String> getHeaderDescription(){
        final HashMap<String, String> result = new HashMap<String, String>();
        result.put(TEMPERATURE, "t °C");
        result.put(COMFORT_TEMPERATURE, "Comfort °C");
        result.put(WATER_TEMPERATURE, "Water °C");
        result.put(WIND_DIRECTION, "Wind Dir");
        result.put(WIND_SPEED, "Wind Speed");
        result.put(PRESSURE, "P");
        result.put(SOURCE, "Сводный прогноз погоды");
        return result;
    }

    public Map<String, String> asMap(){
        final HashMap<String, String> result = new HashMap<String, String>();
        result.put(TEMPERATURE, String.valueOf(temperature));
        result.put(COMFORT_TEMPERATURE, String.valueOf(comfortTemperature));
        result.put(WATER_TEMPERATURE, String.valueOf(waterTemperature));
        result.put(WIND_DIRECTION, String.valueOf(windDirection));
        result.put(WIND_SPEED, String.valueOf(windSpeed));
        result.put(PRESSURE, String.valueOf(pressure));
        result.put(SOURCE, String.valueOf(source));
        return result;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getComfortTemperature() {
        return comfortTemperature;
    }

    public void setComfortTemperature(int comfortTemperature) {
        this.comfortTemperature = comfortTemperature;
    }

    public int getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(int waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public int getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(int windSpeed) {
        this.windSpeed = windSpeed;
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }
}
