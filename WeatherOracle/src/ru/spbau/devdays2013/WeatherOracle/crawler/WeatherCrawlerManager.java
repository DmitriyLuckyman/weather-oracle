package ru.spbau.devdays2013.WeatherOracle.crawler;

import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Dmitriy Bandurin
 *         Date: 13.05.13
 */
public class WeatherCrawlerManager {
    private static WeatherCrawlerManager ourInstance = new WeatherCrawlerManager();
    private Map<String, WeatherCrawler> crawlers = new HashMap<String, WeatherCrawler>();
    private ExecutorService caller = Executors.newFixedThreadPool(3);
    public static WeatherCrawlerManager getInstance() {
        return ourInstance;
    }

    public void registerCrawler(WeatherCrawler crawler){
        crawlers.put(crawler.getID(), crawler);
    }

    public WeatherCrawler deleteCrawler(String crawlerID){
        return crawlers.remove(crawlerID);
    }

    public WeatherCrawler deleteCrawler(WeatherCrawler crawler){
        return deleteCrawler(crawler.getID());
    }

    private WeatherCrawlerManager() {
/*
        registerCrawler(new TestCrawler("Test1", "Яндекс"));
        registerCrawler(new TestCrawler("Test3", "Rambler"));
        registerCrawler(new TestCrawler("Test4", "GisMeteo"));
        registerCrawler(new TestCrawler("Test2", "CC&B News"));
*/
        registerCrawler(new IntellicastCrawler());
        registerCrawler(new GisMeteoCrawler());
        registerCrawler(new YandexCrawler());
        registerCrawler(new MeteoinfoCrawler());
        registerCrawler(new RP5Crawler());
    }

    public List<PredictWeatherBean> getPredictions(){
        List<PredictWeatherBean> result = new ArrayList<PredictWeatherBean>();
        try {
            final List<Future<PredictWeatherBean>> futures = caller.invokeAll(crawlers.values());
            for (Future<PredictWeatherBean> future : futures) {
                result.add(future.get());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return result;
    }
}
