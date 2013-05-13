package ru.spbau.devdays2013.WeatherOracle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import ru.spbau.devdays2013.WeatherOracle.bean.PredictWeatherBean;
import ru.spbau.devdays2013.WeatherOracle.bean.WindDirection;
import ru.spbau.devdays2013.WeatherOracle.crawler.WeatherCrawlerManager;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WeatherOracleActivity extends Activity {

    ListView gridView;
    TextView dateView;
    private SimpleAdapter adapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        configureDateView();
        configureGridView();
    }

    private void configureGridView() {
        gridView = (ListView) findViewById(R.id.listView);
        adapter = new SimpleAdapter(this,
                getData(), R.layout.row_layout,
                new String[]{
                        PredictWeatherBean.TEMPERATURE,
                        PredictWeatherBean.COMFORT_TEMPERATURE,
                        PredictWeatherBean.WATER_TEMPERATURE,
                        PredictWeatherBean.WIND_DIRECTION,
                        PredictWeatherBean.WIND_SPEED,
                        PredictWeatherBean.PRESSURE,
                        PredictWeatherBean.SOURCE
                },
                new int[]{
                        R.id.tempView,
                        R.id.comfortTempView,
                        R.id.waterTempView,
                        R.id.windDirectionView,
                        R.id.windSpeedView,
                        R.id.pressureView,
                        R.id.sourceView
                }
                );
        gridView.setAdapter(adapter);
    }

    private ArrayList<Map<String, ?>> getData() {
        final ArrayList<Map<String, ?>> data = new ArrayList<Map<String, ?>>();
        data.add(PredictWeatherBean.getHeaderDescription());
        final List<PredictWeatherBean> predictions = WeatherCrawlerManager.getInstance().getPredictions();
        for (PredictWeatherBean predictWeatherBean : predictions) {
            data.add(predictWeatherBean.asMap());
        }
        WeatherPredictor predictor = new WeatherPredictor();
        data.add(predictor.getPredict(predictions).asMap());
        return data;
    }

    private void configureDateView() {
        dateView = (TextView) findViewById(R.id.todayTextView);
        dateView.setText(String.format("Сегодня : %s", DateFormat.getDateInstance().format(new Date())));
    }


}
