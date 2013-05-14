package ru.spbau.devdays2013.WeatherOracle.crawler;

import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;
import ru.spbau.devdays2013.WeatherOracle.bean.WindDirection;

import java.util.Date;

/**
 * User: Evgeniy Sluzhaev
 * Date: 5/14/13
 * Time: 12:57 PM
 */
public class RP5Crawler implements WeatherCrawler {
    private final String CRAWLER_ID = "rp5.ru";
    private final String FORECAST_URL = "http://rp5.ru/xml/7285/00000/ru";


    @Override
    public String getID() {
        return CRAWLER_ID;
    }

    @Override
    public void setDate(Date date) {
    }

    @Override
    public PredictWeatherBean call() throws Exception {
        Document doc = Jsoup.connect(FORECAST_URL).get();
        Element tomorrow = doc.getElementsByTag("timestep").get(2);
        Log.d("RP5", tomorrow.getElementsByTag("G").get(0).html());
        Log.d("RP5", tomorrow.getElementsByTag("wind_velocity").get(0).html());
        int temperature = Integer.parseInt(tomorrow.getElementsByTag("G").get(0).html());
        int windVelocity = Integer.parseInt(tomorrow.getElementsByTag("wind_velocity").get(0).html());
        WindDirection windDirection;
        switch (tomorrow.getElementsByTag("wind_direction").get(0).html().charAt(0)) {
            case 'С':
                windDirection = WindDirection.NORTH;
                break;
            case 'З':
                windDirection = WindDirection.WEST;
                break;
            case 'В':
                windDirection = WindDirection.EAST;
                break;
            default:
                windDirection = WindDirection.SOUTH;
                break;
        }
        int pressure = Integer.parseInt(tomorrow.getElementsByTag("pressure").get(0).html());
        return new PredictWeatherBean(temperature, windVelocity, 0, 0, windDirection, pressure, CRAWLER_ID);
    }
}
