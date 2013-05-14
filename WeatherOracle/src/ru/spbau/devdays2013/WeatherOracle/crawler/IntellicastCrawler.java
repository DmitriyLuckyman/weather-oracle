package ru.spbau.devdays2013.WeatherOracle.crawler;

import android.util.Log;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;

import java.util.Date;
import org.jsoup.*;
import ru.spbau.devdays2013.WeatherOracle.bean.WindDirection;

/**
 * Created with IntelliJ IDEA.
 * User: Evgeniy Sluzhaev
 * Date: 5/14/13
 * Time: 2:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class IntellicastCrawler implements WeatherCrawler {
    private final String FORECAST_URL = "http://www.intellicast.com/Local/Forecast.aspx?unit=C&location=RSXX0091";
    private final String SITENAME = "Intellicast";

    @Override
    public String getID() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setDate(Date date) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public PredictWeatherBean call() throws Exception {
        Document doc = Jsoup.connect(FORECAST_URL).get();
        Elements elements = doc.getElementsByAttributeValue("id", "extended");
        Element tomorrow = elements.get(1);
        String tempString = tomorrow.getElementsByClass("Hi").html();
        tempString = tempString.substring(0, tempString.length() - 5);

        Element rightBlock = tomorrow.getElementsByClass("RightColumn").first().child(0);
        String[] tokens = rightBlock.ownText().split(" ");
        String windSpeedToken = tokens[tokens.length - 4];
        int windSpeed = Integer.parseInt(windSpeedToken.substring(1, windSpeedToken.length() - 3));
        String windDirectionToken = tokens[tokens.length - 1];
        WindDirection direction = WindDirection.SOUTH;
        switch (windDirectionToken.charAt(1)) {
            case 'N':
                direction = WindDirection.NORTH;
                break;
            case 'S':
                direction = WindDirection.SOUTH;
            case 'E':
                direction = WindDirection.EAST;
            case 'W':
                direction = WindDirection.WEST;
        }
        Log.d("Intellicast", tokens[tokens.length - 1]);
        int temp = Integer.parseInt(tempString);
        PredictWeatherBean bean = new PredictWeatherBean(temp, windSpeed, 0, 0, direction, 0, SITENAME);
        return bean;
    }
}
