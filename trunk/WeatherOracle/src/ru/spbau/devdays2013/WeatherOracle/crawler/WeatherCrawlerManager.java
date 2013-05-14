package ru.spbau.devdays2013.WeatherOracle.crawler;

import android.util.Log;
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
    private static final WeatherCrawlerManager ourInstance = new WeatherCrawlerManager();
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
                try {
                    result.add(future.get());
                } catch (InterruptedException e) {
                    Log.d("Crawler", "Can't get weather", e);
                } catch (ExecutionException e) {
                    Log.d("Crawler", "Can't get weather", e);
                }
            }
        } catch (InterruptedException e) {
            Log.d("Crawler", "Can't get weather", e);
        }
        return result;
    }
}
