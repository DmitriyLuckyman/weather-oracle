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

    private Integer temperature;
    private Integer comfortTemperature;
    private Integer waterTemperature;
    private Integer windSpeed;
    private String source;
    private WindDirection windDirection;
    private Integer pressure;

    public PredictWeatherBean(Integer temperature, Integer windSpeed, Integer comfortTemperature,
                              Integer waterTemperature, WindDirection windDirection, Integer pressure, String source) {
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
        result.put(TEMPERATURE, toString(temperature));
        result.put(COMFORT_TEMPERATURE, toString(comfortTemperature));
        result.put(WATER_TEMPERATURE, toString(waterTemperature));
        result.put(WIND_DIRECTION, toString(windDirection));
        result.put(WIND_SPEED, toString(windSpeed));
        result.put(PRESSURE, toString(pressure));
        result.put(SOURCE, toString(source));
        return result;
    }

    private String toString(Object s) {
        return s == null ? "-" : String.valueOf(s);
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getComfortTemperature() {
        return comfortTemperature;
    }

    public void setComfortTemperature(Integer comfortTemperature) {
        this.comfortTemperature = comfortTemperature;
    }

    public Integer getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(Integer waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public Integer getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Integer windSpeed) {
        this.windSpeed = windSpeed;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public WindDirection getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(WindDirection windDirection) {
        this.windDirection = windDirection;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }
}
