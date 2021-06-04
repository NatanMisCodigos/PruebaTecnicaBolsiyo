package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model;

import android.content.Context;

import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces.ImageInterfaces;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.rest.ApiAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageInteractor implements ImageInterfaces.Interactor, Callback<ImageApi> {

    private ImageInterfaces.Presenter presenter;
    private ImageApi imageApiList;
    private Context context;

    public ImageInteractor(ImageInterfaces.Presenter presenter, Context context) {
        this.presenter = presenter;
        this.context = context;
    }

    @Override
    public void getUsersFromApi() {
        Call<ImageApi> call = ApiAdapter.getDataUser().getUsers("13119377-fc7e10c6305a7de49da6ecb25");
        System.out.println("REQUEST: " + call.request().toString());
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<ImageApi> call, Response<ImageApi> response) {
        System.out.println("MENSAJE: " + response.toString());
        if (!response.isSuccessful()) {
            presenter.emptyList("Busca una Ciudad");
        } else {
            imageApiList = response.body();

            if(imageApiList.getHits().size() == 0){
                presenter.emptyList("Nada aqui");
                return;
            }

            if (imageApiList != null) {
                presenter.showImageList((ImageApi) imageApiList);
            } else {
                presenter.showMessage("Sin datos");
            }
        }
    }

    @Override
    public void onFailure(Call<ImageApi> call, Throwable t) {
        presenter.showMessage("Error: " + t.getMessage());
    }
}
