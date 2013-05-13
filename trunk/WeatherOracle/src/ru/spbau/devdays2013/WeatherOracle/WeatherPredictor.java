package ru.spbau.devdays2013.WeatherOracle;

import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;

import java.util.List;

/**
 * @author Dmitriy Bandurin
 *         Date: 13.05.13
 */
public class WeatherPredictor {
    public PredictWeatherBean getPredict(List<PredictWeatherBean> predictions) {
        if(!predictions.isEmpty()){
            PredictWeatherBean result  = new PredictWeatherBean(0,0,0,0,predictions.get(0).getWindDirection(),0, "WeatherOracle Prediction");
            for (PredictWeatherBean prediction : predictions) {
                result.setTemperature(result.getTemperature() + prediction.getTemperature());
                result.setComfortTemperature(result.getComfortTemperature() + prediction.getComfortTemperature());
                result.setWaterTemperature(result.getWaterTemperature() + prediction.getWaterTemperature());
                result.setPressure(result.getPressure() + prediction.getPressure());
                result.setWindSpeed(result.getWindSpeed() + prediction.getWindSpeed());
            }
            final int size = predictions.size();
            result.setTemperature(result.getTemperature()/ size);
            result.setComfortTemperature(result.getComfortTemperature() / size);
            result.setWaterTemperature(result.getWaterTemperature() / size);
            result.setPressure(result.getPressure() / size);
            result.setWindSpeed(result.getWindSpeed() / size);
            return result;
        }
        return null;
    }
}
