package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.presenter;

import android.content.Context;

import java.util.List;

import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces.WeatherInterfaces;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.Weather;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.WeatherInteractor;

public class WeatherPresenter implements WeatherInterfaces.Presenter {

    private WeatherInterfaces.View view;
    private WeatherInterfaces.Interactor interactor;

    public WeatherPresenter(WeatherInterfaces.View view, Context context) {
        this.view = view;
        this.interactor = new WeatherInteractor(this, context);
    }

    @Override
    public void showUserPosts(List<Weather> weather) {
        view.showUserPosts(weather);
    }

    @Override
    public void getUserPosts(String woeid, String year, String month, String day) {
        interactor.getUserPosts(woeid, year, month, day);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }
}
