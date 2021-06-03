package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model;

import android.content.Context;

import com.example.pruebatecnicabold.interfaces.LocationInterfaces;
import com.example.pruebatecnicabold.rest.ApiAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationInteractor implements LocationInterfaces.Interactor, Callback<List<Location>> {

    private LocationInterfaces.Presenter presenter;
    private List<Location> locationList;
    private Context context;

    public LocationInteractor(LocationInterfaces.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
        this.locationList = new ArrayList<>();
    }

    @Override
    public void getUsersFromApi(String location) {
        Call<List<Location>> call = ApiAdapter.getDataUser().getUsers(location);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Location>> call, Response<List<Location>> response) {
        if (!response.isSuccessful()) {
            presenter.emptyList("Busca una Ciudad");
        } else {
            locationList = response.body();

            if(locationList.size() == 0){
                presenter.emptyList("Nada aqui");
                return;
            }

            if (locationList != null) {
                presenter.showUsers((ArrayList<Location>) locationList);
            } else {
                presenter.showMessage("Sin datos");
            }
        }
    }

    @Override
    public void onFailure(Call<List<Location>> call, Throwable t) {
        presenter.showMessage("Error: " + t.getMessage());
    }
}
