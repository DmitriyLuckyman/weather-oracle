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
 * Time: 2:10 PM
 */
public class MeteoinfoCrawler implements WeatherCrawler {
    private final String CRAWLER_ID = "meteoinfo";
    private final String FORECAST_URL = "http://meteoinfo.ru/rss/forecasts/26063";

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
        Element tomorrow = doc.getElementsByTag("item").get(1);
        String description = tomorrow.getElementsByTag("description").get(0).html();
        int tempIndexInit = description.indexOf("Температура");
        int tempIndexDay = description.indexOf("днём", tempIndexInit) + 5;
        String tempToken = "";
        while(description.charAt(tempIndexDay) == '-' || Character.isDigit(description.charAt(tempIndexDay))) {
            tempToken += description.charAt(tempIndexDay);
            ++tempIndexDay;
        }
        int temperature = Integer.parseInt(tempToken);

        int windIndexInit = description.indexOf("Ветер") + 6;
        String windDirectionToken = "";
        while(description.charAt(windIndexInit) != ' ') {
            windDirectionToken += description.charAt(windIndexInit);
            ++windIndexInit;
        }
        windDirectionToken = windDirectionToken.substring(0, windDirectionToken.length() - 1);
        String windVelocityToken = "";
        ++windIndexInit;
        while(Character.isDigit(description.charAt(windIndexInit))) {
            windVelocityToken += description.charAt(windIndexInit);
            ++windIndexInit;
        }

        int windVelocity = Integer.parseInt(windVelocityToken);

        Log.d("meteoinfo", windDirectionToken);
        WindDirection windDirection;
        if(windDirectionToken.equals("северный")) {
            windDirection = WindDirection.NORTH;
        } else if(windDirectionToken.equals("южный")) {
            windDirection = WindDirection.SOUTH;
        } else if(windDirectionToken.equals("западный")) {
            windDirection = WindDirection.WEST;
        } else if(windDirectionToken.equals("восточный")) {
            windDirection = WindDirection.EAST;
        } else if(windDirectionToken.equals("северо-восточный")) {
            windDirection = WindDirection.NORTH_EAST;
        } else if(windDirectionToken.equals("северо-западный")) {
            windDirection = WindDirection.NORTH_WEST;
        } else if(windDirectionToken.equals("юго-восточный")) {
            windDirection = WindDirection.SOUTH_EAST;
        } else {
            windDirection = WindDirection.SOUTH_WEST;
        }
        return new PredictWeatherBean(temperature, windVelocity, null, null, windDirection, null, CRAWLER_ID);
    }
}
