package ru.spbau.devdays2013.WeatherOracle.crawler;

import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author Dmitriy Bandurin
 *         Date: 13.05.13
 */
public interface WeatherCrawler extends Callable<PredictWeatherBean> {
    String getID();
    void setDate(Date date);
}
