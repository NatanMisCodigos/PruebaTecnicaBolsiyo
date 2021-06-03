package co.com.bolsiyo.mobile.pruebatecnicabolsiyo.interfaces;


import com.example.pruebatecnicabold.model.Location;

import java.util.ArrayList;

public interface LocationInterfaces {

    interface View{
        void showUsers(ArrayList<Location> usersList);
        void showMessage(String message);
        void emptyList(String message);
    }

    interface Interactor{
        void getUsersFromApi(String location);
    }

    interface Presenter{
        void emptyList(String message);
        void showUsers(ArrayList<Location> usersList);
        void showMessage(String message);
        void getUsersFromApi(String location);
    }

}
