package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces;


import java.util.ArrayList;

import co.com.bolsiyo.mobile.pruebatecnicabolsiyo.model.ImageApi;

public interface ImageInterfaces {

    interface View{
        void showImageList(ImageApi imageList);
        void showMessage(String message);
        void emptyList(String message);
        void showDetailsImage(ImageApi.Hits image);
    }

    interface Interactor{
        void getUsersFromApi();
    }

    interface Presenter{
        void emptyList(String message);
        void showImageList(ImageApi imageList);
        void showMessage(String message);
        void showDetailsImage(ImageApi.Hits image);
        void getUsersFromApi();
    }

}
