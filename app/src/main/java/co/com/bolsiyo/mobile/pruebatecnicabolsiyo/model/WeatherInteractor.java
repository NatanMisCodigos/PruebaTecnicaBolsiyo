package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model;

import android.content.Context;

import com.example.pruebatecnicabold.R;
import com.example.pruebatecnicabold.interfaces.WeatherInterfaces;
import com.example.pruebatecnicabold.rest.ApiAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherInteractor implements WeatherInterfaces.Interactor, Callback<List<Weather>> {

    private List<Weather> weatherList = new ArrayList<>();
    private WeatherInterfaces.Presenter presenter;
    private Context context;
    private byte index = 0;
    private String woeid, year, month, day;

    public WeatherInteractor(WeatherInterfaces.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getUserPosts(String woeid, String year, String month, String day) {
        if(index == 0){
            this.woeid = woeid;
            this.year = year;
            this.month = month;
            this.day = day;
        }
        Call<List<Weather>> call = ApiAdapter.getDataUser().getWeatherLocation(woeid,
                year, month, day);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Weather>> call, Response<List<Weather>> response) {
        if (!response.isSuccessful()) {
            presenter.showMessage(context.getString(R.string.generic_error) + " " + response.code());
        } else {
            weatherList.add(response.body().get(0));

            if(index == 4){
                presenter.showUserPosts(weatherList);
                return;
            }

            index++;
            System.out.println("EL ANTIGUO ERA " + day);
            day = "" + (Integer.parseInt(day) + 1);
            System.out.println("EL NUEVO ERA " + day);
            getUserPosts(woeid, year, month, day);
        }
    }

    @Override
    public void onFailure(Call<List<Weather>> call, Throwable t) {
        presenter.showMessage("Error: " + t.getLocalizedMessage());
        System.out.println("Error: " + t.getLocalizedMessage());
    }
}
