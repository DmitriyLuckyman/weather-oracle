package ru.spbau.devdays2013.WeatherOracle.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;
import ru.spbau.devdays2013.WeatherOracle.bean.WindDirection;


/**
 * @author Dmitriy Bandurin
 *         Date: 14.05.13
 */
public class GisMeteoCrawler implements WeatherCrawler {

    public static final String CRAWLER_ID = "GISMETEO";

    @Override
    public String getID() {
        return CRAWLER_ID;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public PredictWeatherBean call() throws Exception {
        Document doc = Jsoup.connect("http://informer.gismeteo.ru/xml/26063_1.xml").get();
        final Element forecast = doc.getElementsByTag("FORECAST").get(2);
        return new PredictWeatherBean(
                        Integer.valueOf(forecast.getElementsByTag("TEMPERATURE").get(0).attr("max")),
                Integer.valueOf(forecast.getElementsByTag("WIND").get(0).attr("max")),
                Integer.valueOf(forecast.getElementsByTag("HEAT").get(0).attr("max")),
                Integer.valueOf(forecast.getElementsByTag("TEMPERATURE").get(0).attr("max")) - 5,
                WindDirection.getByIntValue(Integer.valueOf(forecast.getElementsByTag("WIND").get(0).attr("direction"))),
                Integer.valueOf(forecast.getElementsByTag("PRESSURE").get(0).attr("max")),
                "GisMeteo"
        );
    }
}
