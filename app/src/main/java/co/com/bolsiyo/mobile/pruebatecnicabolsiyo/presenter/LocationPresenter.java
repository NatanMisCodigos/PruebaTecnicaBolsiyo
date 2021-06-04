package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.presenter;

import android.content.Context;

import java.util.ArrayList;

import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces.LocationInterfaces;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.Location;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.LocationInteractor;

public class LocationPresenter implements LocationInterfaces.Presenter {

    LocationInterfaces.View view;
    LocationInterfaces.Interactor interactor;

    public LocationPresenter(LocationInterfaces.View view, Context context) {
        this.view = view;
        this.interactor = new LocationInteractor(this, context);
    }

    @Override
    public void emptyList(String message) {
        if(view != null)
            view.emptyList(message);
    }

    @Override
    public void showUsers(ArrayList<Location> locations) {
        view.showUsers(locations);
    }

    @Override
    public void showMessage(String message) {
        view.showMessage(message);
    }

    @Override
    public void getUsersFromApi(String location) {
        if(interactor != null)
            interactor.getUsersFromApi(location);
    }
}
