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
            int temperatureSize = 0;
            int comfortTemperatureSize = 0;
            int waterTemperatureSize = 0;
            int pressureSize = 0;
            int windSpeedSize = 0;
            for (PredictWeatherBean prediction : predictions) {
                if(prediction.getTemperature() != null) {
                    result.setTemperature(result.getTemperature() + prediction.getTemperature());
                    ++temperatureSize;
                }
                if(prediction.getComfortTemperature() != null) {
                    result.setComfortTemperature(result.getComfortTemperature() + prediction.getComfortTemperature());
                    ++comfortTemperatureSize;
                }
                if(prediction.getWaterTemperature() != null) {
                    result.setWaterTemperature(result.getWaterTemperature() + prediction.getWaterTemperature());
                    ++waterTemperatureSize;
                }
                if(prediction.getPressure() != null) {
                    result.setPressure(result.getPressure() + prediction.getPressure());
                    ++pressureSize;
                }
                if(prediction.getWindSpeed() != null) {
                    result.setWindSpeed(result.getWindSpeed() + prediction.getWindSpeed());
                    ++windSpeedSize;
                }
            }
            result.setTemperature(result.getTemperature() / temperatureSize);
            result.setComfortTemperature(result.getComfortTemperature() / comfortTemperatureSize);
            result.setWaterTemperature(result.getWaterTemperature() / waterTemperatureSize);
            result.setPressure(result.getPressure() / pressureSize);
            result.setWindSpeed(result.getWindSpeed() / windSpeedSize);
            return result;
        }
        return null;
    }
}
