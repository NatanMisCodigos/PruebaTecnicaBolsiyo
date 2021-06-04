package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.presenter;

import android.content.Context;

import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces.ImageInterfaces;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.ImageApi;
import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.ImageInteractor;

public class ImagePresenter implements ImageInterfaces.Presenter {

    ImageInterfaces.View view;
    ImageInterfaces.Interactor interactor;

    public ImagePresenter(ImageInterfaces.View view, Context context) {
        this.view = view;
        this.interactor = new ImageInteractor(this, context);
    }

    @Override
    public void emptyList(String message) {
        if(view != null)
            view.emptyList(message);
    }

    @Override
    public void showImageList(ImageApi imageList) {
        if(view != null)
            view.showImageList(imageList);
    }

    @Override
    public void showMessage(String message) {
        if(view != null)
            view.showMessage(message);
    }

    @Override
    public void showDetailsImage(ImageApi.Hits image) {
        if(view != null)
            view.showDetailsImage(image);
    }

    @Override
    public void getUsersFromApi() {
        if(interactor != null)
            interactor.getUsersFromApi();
    }
}
