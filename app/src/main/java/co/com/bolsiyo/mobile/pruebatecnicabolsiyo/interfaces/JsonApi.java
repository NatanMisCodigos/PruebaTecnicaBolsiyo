package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces;

import com.example.pruebatecnicabold.model.Location;
import com.example.pruebatecnicabold.model.Weather;
import com.example.pruebatecnicabold.rest.Endpoints;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonApi {

    @GET(Endpoints.GET_LOCATION)
    Call<List<Location>> getUsers(@Query("query") String location);

    @GET("{woeid}/{year}/{month}/{day}")
    Call<List<Weather>> getWeatherLocation(@Path("woeid") String woeid,
                                           @Path("year") String year,
                                           @Path("month") String month,
                                           @Path("day") String day);
}
