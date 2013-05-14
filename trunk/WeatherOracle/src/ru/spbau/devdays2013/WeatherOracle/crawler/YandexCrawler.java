package ru.spbau.devdays2013.WeatherOracle.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;
import ru.spbau.devdays2013.WeatherOracle.bean.WindDirection;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Jeannette
 * Date: 14.05.13
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
public class YandexCrawler implements WeatherCrawler {
    public static final String CRAWLER_ID = "YANDEX";

    @Override
    public String getID() {
        return CRAWLER_ID;
    }

    @Override
    public void setDate(Date date) {
    }

    @Override
    public PredictWeatherBean call() throws Exception {

        Document doc = Jsoup.connect("http://m.pogoda.yandex.ru/2/")
                .get();

        Elements elements = doc.getElementsByAttributeValue("class", "b-now");
        Element now = elements.first();
        String tempString = now.getElementsByTag("strong").first().ownText();
        int  p = tempString.length() - 3;
        tempString = tempString.substring(1, p);
        int mul = 1;
        if (tempString.charAt(0)=='-')
        {
            mul = -1;
        }

        int temp = Integer.parseInt(tempString);

        temp*=mul;
        String waterString = now.getElementsByTag("p").get(1).ownText();
        p = waterString.length() - 3;
        mul = 1;
        if (waterString.charAt(6)=='-')
        {
            mul = -1;
        }
        String waterTemperature = waterString.substring(7, p).trim();
        int water = Integer.parseInt(waterTemperature);
        water *= mul;
        String windString = now.getElementsByTag("p").get(2).ownText();
        String[] numbers = windString.split(" ");
        p = numbers[1].length() - 1;
        String windDirection = numbers[1].substring(0, p);
        WindDirection direction = WindDirection.WEST;
        /*switch (windDirection.charAt(0)) {
            case 'С':
                direction = WindDirection.NORTH;
                break;
            case 'Ю':
                direction = WindDirection.SOUTH;
                break;
            case 'В':
                direction = WindDirection.EAST;
                break;
            case 'З':
                direction = WindDirection.WEST;
                break;
            default:
                direction = WindDirection.SOUTH;
                break;
        }  */
        p = numbers[2].length() - 5;
        int windSpeed = (int)Double.parseDouble(numbers[2].substring(0, p));
        p = numbers[4].length() - 1;
        int hydration = Integer.parseInt(numbers[4].substring(0, p));

        String pressureString = now.getElementsByTag("p").get(3).ownText().split(" ")[1];
        p = pressureString.length() - 3;
        int pressure = Integer.parseInt(pressureString.substring(0, p));
        return new PredictWeatherBean(
                temp,
                windSpeed,
                0,
                water,
                direction,
                pressure,
                "Yandex"
        );
        //return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
