package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces;

import java.util.List;

import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.ImageApi;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.Weather;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApi {

    @GET(".")
    Call<ImageApi> getUsers(@Query("key") String key);

    @GET("{woeid}/{year}/{month}/{day}")
    Call<List<Weather>> getWeatherLocation(@Path("woeid") String woeid,
                                           @Path("year") String year,
                                           @Path("month") String month,
                                           @Path("day") String day);
}
