package ru.spbau.devdays2013.WeatherOracle.crawler;

import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;
import ru.spbau.devdays2013.WeatherOracle.bean.WindDirection;

import java.util.Date;
import java.util.Random;

/**
 * @author Dmitriy Bandurin
 *         Date: 13.05.13
 */
public class TestCrawler implements WeatherCrawler {
    private Date date;
    private String description;
    private String id;

    public TestCrawler(String id, String description) {
        this.description = description;
        this.id = id;
    }

    @Override


    public String getID() {
        return id;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public PredictWeatherBean call() throws Exception {
        if(this.date == null){
            this.date = new Date();
        }
        Random r = new Random();
        Thread.sleep(1000);
        return new PredictWeatherBean(r.nextInt(50),
                r.nextInt(50),
                r.nextInt(50),
                r.nextInt(50),
                WindDirection.NORTH,
                r.nextInt(1000),
                description);
    }
}
