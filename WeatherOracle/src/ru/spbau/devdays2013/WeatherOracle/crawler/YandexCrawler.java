package ru.spbau.devdays2013.WeatherOracle.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;
import ru.spbau.devdays2013.WeatherOracle.bean.WindDirection;
/**
 * User: Jeannette
 * Date: 14.05.13
 */
public class YandexCrawler implements WeatherCrawler {
    public static final String CRAWLER_ID = "YANDEX";

    @Override
    public String getID() {
        return CRAWLER_ID;
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
        WindDirection direction = WindDirection.WEST;
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
                hydration,
                water,
                direction,
                pressure,
                "Yandex"
        );
    }
}
